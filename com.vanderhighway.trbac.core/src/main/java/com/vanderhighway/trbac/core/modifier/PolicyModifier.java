package com.vanderhighway.trbac.core.modifier;

import com.brein.time.timeintervals.intervals.IntegerInterval;
import com.google.common.base.Objects;
import com.vanderhighway.trbac.model.trbac.model.*;
import com.vanderhighway.trbac.model.trbac.model.TRBACPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.ModelManipulationException;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation;
import org.eclipse.xtext.xbase.lib.Extension;

import java.lang.reflect.InvocationTargetException;
import java.time.Period;
import java.util.concurrent.Callable;

public class PolicyModifier {

	/**
	 * Transformation-related extensions
	 */

	@Extension
	public IModelManipulations manipulation;

	@Extension
	private BatchTransformationRuleFactory batchFactory = new BatchTransformationRuleFactory();

	@Extension
	private BatchTransformation transformation;

	@Extension
	private TRBACPackage ePackage = TRBACPackage.eINSTANCE;

	private AdvancedViatraQueryEngine engine;

	private Policy policy;
	private Resource resource;

	public PolicyModifier(final AdvancedViatraQueryEngine engine, Policy policy, Resource resource) {
		this.engine = engine;
		this.manipulation = new SimpleModelManipulations(this.engine);
		this.policy = policy;
		this.resource = resource;
		//this.transformation = BatchTransformation.forEngine(this.engine).build();
	}

	// ---------- Add / Remove Authorization Model Entities ----------

	public User addUser(String name) throws ModelManipulationException {
		User user = (User) manipulation.createChild(policy, ePackage.getPolicy_Users(), ePackage.getUser());
		manipulation.set(user, ePackage.getUser_Name(), name);
		return user;
	}

	public void removeUser(User user) throws ModelManipulationException {
		manipulation.remove(user);
	}

	public Role addRole(String name) throws ModelManipulationException {
		Role role = (Role) manipulation.createChild(policy, ePackage.getPolicy_Roles(), ePackage.getRole());
		manipulation.set(role, ePackage.getRole_Name(), name);
		return role;
	}

	public void removeRole(Role role) throws ModelManipulationException {
		manipulation.remove(role);
	}

	public Demarcation addDemarcation(String name) throws ModelManipulationException {
		Demarcation demarcation = (Demarcation) manipulation.createChild(policy, ePackage.getPolicy_Demarcations(), ePackage.getDemarcation());
		manipulation.set(demarcation, ePackage.getDemarcation_Name(), name);
		return demarcation;
	}

	public void removeDemarcation(Demarcation demarcation) throws ModelManipulationException {
		manipulation.remove(demarcation);
	}

	public Permission addPermission(String name) throws ModelManipulationException {
		Permission permission = (Permission) manipulation.createChild(policy, ePackage.getPolicy_Permissions(), ePackage.getPermission());
		manipulation.set(permission, ePackage.getPermission_Name(), name);
		return permission;
	}

	public void removePermission(Permission permission) throws ModelManipulationException {
		manipulation.remove(permission);
	}

	// -----------------------------------------------


	// ---------- Add / Remove Authorization Model Relations ----------

	public void assignRoleToUser(User user, Role role) throws ModelManipulationException {
		manipulation.addTo(user, ePackage.getUser_UR(), role);
	}

	public void deassignRoleFromUser(User user, Role role) throws ModelManipulationException {
		manipulation.remove(user, ePackage.getUser_UR(), role);
	}

	public void assignDemarcationToRole(Role role, Demarcation demarcation) throws ModelManipulationException {
		manipulation.addTo(role, ePackage.getRole_RD(), demarcation);
	}

	public void deassignDemarcationFromRole(Role role, Demarcation demarcation) throws ModelManipulationException {
		manipulation.remove(role, ePackage.getRole_RD(), demarcation);
	}

	public void assignPermissionToDemarcation(Demarcation demarcation, Permission permission) throws ModelManipulationException {
		manipulation.addTo(demarcation, ePackage.getDemarcation_DP(), permission);
	}

	public void deassignPermissionFromDemarcation(Demarcation demarcation, Permission permission) throws ModelManipulationException {
		manipulation.remove(demarcation, ePackage.getDemarcation_DP(), permission);
	}

	public void addRoleInheritance(Role juniorRole, Role seniorRole) throws ModelManipulationException {
		manipulation.addTo(juniorRole, ePackage.getRole_Seniors(), seniorRole);
	}

	public void removeRoleInheritance(Role juniorRole, Role seniorRole) throws ModelManipulationException {
		manipulation.remove(juniorRole, ePackage.getRole_Seniors(), seniorRole);
	}

	public void addDemarcationInheritance(Demarcation subdemarcation, Demarcation supdemarcation) throws ModelManipulationException {
		manipulation.addTo(subdemarcation, ePackage.getDemarcation_Superdemarcations(), supdemarcation);
	}

	public void removeDemarcationInheritance(Demarcation subdemarcation, Demarcation supdemarcation) throws ModelManipulationException {
		manipulation.remove(subdemarcation, ePackage.getDemarcation_Superdemarcations(), supdemarcation);
	}

	// -------------------------------------------

	public void dispose() {
		if (!Objects.equal(this.transformation, null)) {
			this.transformation.dispose();
		}
		this.transformation = null;
		return;
	}


}
