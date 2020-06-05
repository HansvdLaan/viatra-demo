package com.vanderhighway.trbac.verifier;

import com.brein.time.timeintervals.collections.ListIntervalCollection;
import com.brein.time.timeintervals.indexes.IntervalTree;
import com.brein.time.timeintervals.indexes.IntervalTreeBuilder;
import com.brein.time.timeintervals.intervals.IInterval;
import com.brein.time.timeintervals.intervals.IdInterval;
import com.brein.time.timeintervals.intervals.IntegerInterval;
import com.google.common.base.Objects;
import com.vanderhighway.trbac.model.trbac.model.TRBACPackage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class PolicyValidator {


    @Extension
    private Logger logger = Logger.getLogger(PolicyValidator.class);

    protected AdvancedViatraQueryEngine engine;

    private IntervalTree tree;
    Map<IntegerInterval, EObject> intervalmap;

    public PolicyValidator(final AdvancedViatraQueryEngine engine) {
        this.engine = engine;
        this.addChangeListeners(engine);
    }

    //TODO: move this to somewhere else!
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

        //engine.addMatchUpdateListener(MissingInheritedDemarcation.Matcher.on(engine), ListenerFactory.getMissingInheritedDemarcationUpdateListener(), true);
        //engine.addMatchUpdateListener(InheritedDemarcation.Matcher.on(engine), ListenerFactory.getInheritedDemarcationUpdateListener(), true);

        // Add Access Relation Listener
        engine.addMatchUpdateListener(AllJuniors.Matcher.on(engine), ListenerFactory.getAllJuniorsUpdateListener(), true);
        //engine.addMatchUpdateListener(AccessRelation.Matcher.on(engine), ListenerFactory.getAccessRelationUpdateListener(), true);
        engine.addMatchUpdateListener(AccessRelation2.Matcher.on(engine), ListenerFactory.getAccessRelation2UpdateListener(), true);
//        engine.addMatchUpdateListener(DayRange.Matcher.on(engine), ListenerFactory.getDayRangeUpdateListener(), true);
//        engine.addMatchUpdateListener(NonOverlappingDayRange.Matcher.on(engine), ListenerFactory.getNonOverlappingDayRangeUpdateListener(), true);
        engine.addMatchUpdateListener(Range.Matcher.on(engine), ListenerFactory.getRangeUpdateListener(), true);
        engine.addMatchUpdateListener(ScheduleRange.Matcher.on(engine), ListenerFactory.getScheduleRangeUpdateListener(), true);
        engine.addMatchUpdateListener(TimeRange.Matcher.on(engine), ListenerFactory.getTimeRangeUpdateListener(), true);
    }

}
