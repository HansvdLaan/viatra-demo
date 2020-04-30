package com.vanderhighway.trbac.verifier;

import com.google.common.base.Objects;
import com.vanderhighway.trbac.model.trbac.model.PetriNetPackage;
import com.vanderhighway.trbac.model.trbac.model.Role;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.transformation.evm.specific.Lifecycles;
import org.eclipse.viatra.transformation.evm.specific.crud.CRUDActivationStateEnum;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.ModelManipulationException;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements;
import org.eclipse.viatra.transformation.runtime.emf.transformation.eventdriven.EventDrivenTransformation;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class PolicyValidator {
  @Extension
  private Logger logger = Logger.getLogger(PolicyValidator.class);
  
  /**
   * Transformation-related extensions
   */
  @Extension
  private EventDrivenTransformation transformation;
  
  /**
   * Transformation rule-related extensions
   */
  @Extension
  private EventDrivenTransformationRuleFactory _eventDrivenTransformationRuleFactory = new EventDrivenTransformationRuleFactory();
  
  @Extension
  private IModelManipulations manipulation;
  
  @Extension
  private BatchTransformationRuleFactory batchFactory = new BatchTransformationRuleFactory();
  
  @Extension
  private BatchTransformation transformation2;
  
  @Extension
  private BatchTransformationStatements statements;
  
  @Extension
  private PetriNetPackage ePackage = PetriNetPackage.eINSTANCE;
  
  protected AdvancedViatraQueryEngine engine;
  
  protected Resource resource;
  
  public PolicyValidator(final AdvancedViatraQueryEngine engine) {
    this.engine = engine;
    this.transformation = createTransformation();
    this.logger.info("Preparing transformation rules.");
    this.createTransformation();
    this.logger.info("Prepared transformation rules");
    
    this.transformation2 = createTransformation2();
    
    this.addChangeListeners(engine);
  }
  
  public void execute() {
    this.logger.debug("Executing transformation on:�resource.URI�");
    this.transformation.getExecutionSchema().startUnscheduledExecution();
    //dispose();
    this.transformation2.getTransformationStatements().fireOne(addTestRole("R4"));
    this.transformation2.getTransformationStatements().fireOne(removeRole("R4"));
    this.transformation2.getTransformationStatements().fireOne(removeRole("R1"));
    dispose();
    
  }
  
	private void addChangeListeners(AdvancedViatraQueryEngine engine) {
			// fireNow = true parameter means all current matches are sent to the listener

			// Add some other Listeners
			//engine.addMatchUpdateListener(UserShouldHaveARole.Matcher.on(engine), ListenerFactory.getUserShouldHaveARoleUpdateListener(), true);
			//engine.addMatchUpdateListener(RoleShouldHaveADemarcation.Matcher.on(engine), ListenerFactory.getRoleShouldHaveADemarcationUpdateListener(), true);
			//engine.addMatchUpdateListener(DemarcationShouldHaveAPermission.Matcher.on(engine), ListenerFactory.getDemarcationShouldHaveAPermissionUpdateListener(), true);

			//engine.addMatchUpdateListener(OnlyOneDirector.Matcher.on(engine), ListenerFactory.getOnlyOneDirectorUpdateListeer(), true);
			//engine.addMatchUpdateListener(OnlyOneRnDManager.Matcher.on(engine), ListenerFactory.getOnlyOneRnDManagerUpdateListener(), true);
			//engine.addMatchUpdateListener(OnlyOneOperationsManager.Matcher.on(engine), ListenerFactory.getOnlyOneOperationsManagerUpdateListener(), true);

			//engine.addMatchUpdateListener(SoDEmployeeAndContractor.Matcher.on(engine), ListenerFactory.getSoDEmployeeAndContractorUpdateListener(), true);
			//engine.addMatchUpdateListener(SoDEmployeeAndVisitor.Matcher.on(engine), ListenerFactory.getSoDEmployeeAndVisitorUpdateListener(), true);

			//engine.addMatchUpdateListener(PrerequisiteEverybodyHasAccessToLobby.Matcher.on(engine), ListenerFactory.getPrerequisiteEveryHasAccessToLobbyUpdateListener(), true);
			//engine.addMatchUpdateListener(PrerequisiteVaultImpliesOpenOffice.Matcher.on(engine), ListenerFactory.getPrerequisiteVaultImpliesOpenOfficeUpdateListener(), true);

			// Add Role Name Listener
			engine.addMatchUpdateListener(RoleName.Matcher.on(engine), ListenerFactory.getRoleNameMatchUpdateListener(), true);
			
			engine.addMatchUpdateListener(MissingInheritedDemarcation.Matcher.on(engine), ListenerFactory.getMissingInheritedDemarcationUpdateListener(), true);
			engine.addMatchUpdateListener(InheritedDemarcation.Matcher.on(engine), ListenerFactory.getInheritedDemarcationUpdateListener(), true);
			
			// Add Access Relation Listener
			//engine.addMatchUpdateListener(AccessRelation.Matcher.on(engine), ListenerFactory.getAccessRelationUpdateListener(), true);
	}
	
  private EventDrivenTransformation createTransformation() {
    EventDrivenTransformation transformation = null;
    this.manipulation = new SimpleModelManipulations(this.engine);
    transformation = EventDrivenTransformation.forEngine(this.engine).addRule(this.addInheritedDemarcations()).build();
    return transformation;
  }
  
  private BatchTransformation createTransformation2() {
	 BatchTransformation transformation = null;
	 transformation = BatchTransformation.forEngine(this.engine).build();
	 return transformation;
  }
  
  private EventDrivenTransformationRule<MissingInheritedDemarcation.Match, MissingInheritedDemarcation.Matcher> addInheritedDemarcations() {
    final Consumer<MissingInheritedDemarcation.Match> function = (MissingInheritedDemarcation.Match it) -> {
    	it.getRoleSenior().getRDH().add(it.getDemarcation());
    };

    //final Consumer<MissingInheritedDemarcation.Match> _function_1 = (MissingInheritedDemarcation.Match it) -> { };
    //final Consumer<MissingInheritedDemarcation.Match> _function_2 = (MissingInheritedDemarcation.Match it) -> {};
    final EventDrivenTransformationRule<MissingInheritedDemarcation.Match, MissingInheritedDemarcation.Matcher> exampleRule =
    		this._eventDrivenTransformationRuleFactory.createRule(MissingInheritedDemarcation.instance()).action(
      CRUDActivationStateEnum.CREATED, function).action(
      CRUDActivationStateEnum.UPDATED, (MissingInheritedDemarcation.Match it) -> { }).action(
      CRUDActivationStateEnum.DELETED, (MissingInheritedDemarcation.Match it) -> { }).addLifeCycle(Lifecycles.getDefault(true, true)).build();
    return exampleRule;
  }
  
  private BatchTransformationRule<Policy.Match, Policy.Matcher> addTestRole(String name) {
	    final Consumer<Policy.Match> function = (Policy.Match it) -> {
	    	try {
				Role role = (Role) this.manipulation.createChild(it.getPolicy(), this.ePackage.getPolicy_Roles(), this.ePackage.getRole());
				//role.setName(name);
				this.manipulation.set(role, this.ePackage.getRole_Name(), name);
				this.manipulation.add(role, this.ePackage.getRole_Juniors(), it.getPolicy().getRoles().get(1));
				//this.manipulation.
			} catch (ModelManipulationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    };
	    final BatchTransformationRule<Policy.Match, Policy.Matcher> exampleRule =
	    		this.batchFactory.createRule(Policy.instance()).action(function).build();
	    return exampleRule;
	  }
  
  private BatchTransformationRule<Policy.Match, Policy.Matcher> removeRole(String name) {
	    final Consumer<Policy.Match> function = (Policy.Match it) -> {
	    	try {
	    		EList<Role> Eroles = it.getPolicy().getRoles();
	    		List<Role> roles = Eroles.stream().filter(role -> role.getName().equals(name)).collect(Collectors.toList());
	    		Role role = roles.get(0);
	    		this.manipulation.remove(role);
			} catch (ModelManipulationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    };
	    final BatchTransformationRule<Policy.Match, Policy.Matcher> exampleRule =
	    		this.batchFactory.createRule(Policy.instance()).action(function).build();
	    return exampleRule;
	  }

  public void dispose() {
    if (!Objects.equal(this.transformation, null)) {
      this.transformation.dispose();
    }
    this.transformation = null;
    return;
  }
}
