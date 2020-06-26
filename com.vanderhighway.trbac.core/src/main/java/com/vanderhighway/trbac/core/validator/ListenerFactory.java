package com.vanderhighway.trbac.core.validator;

import com.vanderhighway.trbac.model.trbac.model.TimeRangeGroup;
import com.vanderhighway.trbac.patterns.*;
import org.eclipse.viatra.query.runtime.api.IMatchUpdateListener;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ListenerFactory {

	public static Set<IMatchUpdateListener> getALlUpdateListeners() {
		Set<IMatchUpdateListener> updateListeners = new HashSet();
		updateListeners.add(getRoleNameMatchUpdateListener());
		updateListeners.add(getUserShouldHaveARoleUpdateListener());
		updateListeners.add(getRoleShouldHaveADemarcationUpdateListener());
		updateListeners.add(getDemarcationShouldHaveAPermissionUpdateListener());
		updateListeners.add(getOnlyOneDirectorUpdateListeer());
		updateListeners.add(getOnlyOneRnDManagerUpdateListener());
		updateListeners.add(getOnlyOneOperationsManagerUpdateListener());
		updateListeners.add(getSoDEmployeeAndContractorUpdateListener());
		updateListeners.add(getSoDEmployeeAndVisitorUpdateListener());
		//updateListeners.add(getPrerequisiteEveryHasAccessToLobbyUpdateListener());
		//updateListeners.add(getPrerequisiteVaultImpliesOpenOfficeUpdateListener());
		updateListeners.add(getAccessRelationUpdateListener());
		return updateListeners;
	}

	public static IMatchUpdateListener<RoleName.Match> getRoleNameMatchUpdateListener() {
		return new IMatchUpdateListener<RoleName.Match>() {
			@Override
			public void notifyAppearance(RoleName.Match match) {
				System.out.printf("[ADD RoleName Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(RoleName.Match match) {
				System.out.printf("[REM RoleName Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<UserShouldHaveARole.Match> getUserShouldHaveARoleUpdateListener() {
		return new IMatchUpdateListener<UserShouldHaveARole.Match>() {
			@Override
			public void notifyAppearance(UserShouldHaveARole.Match match) {
				System.out.printf("[ADD UserShouldHaveARole Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(UserShouldHaveARole.Match match) {
				System.out.printf("[REM UserShouldHaveARole Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<RoleShouldHaveADemarcation.Match> getRoleShouldHaveADemarcationUpdateListener() {
		return new IMatchUpdateListener<RoleShouldHaveADemarcation.Match>() {
			@Override
			public void notifyAppearance(RoleShouldHaveADemarcation.Match match) {
				System.out.printf("[ADD RoleShouldHaveADemarcation Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(RoleShouldHaveADemarcation.Match match) {
				System.out.printf("[REM RoleShouldHaveADemarcation Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<DemarcationShouldHaveAPermission.Match> getDemarcationShouldHaveAPermissionUpdateListener() {
		return new IMatchUpdateListener<DemarcationShouldHaveAPermission.Match>() {
			@Override
			public void notifyAppearance(DemarcationShouldHaveAPermission.Match match) {
				System.out.printf("[ADD DemarcationShouldHaveAPermission Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(DemarcationShouldHaveAPermission.Match match) {
				System.out.printf("[REM DemarcationShouldHaveAPermission Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<OnlyOneDirector.Match> getOnlyOneDirectorUpdateListeer() {
		return new IMatchUpdateListener<OnlyOneDirector.Match>() {
			@Override
			public void notifyAppearance(OnlyOneDirector.Match match) {
				System.out.printf("[ADD OnlyOneDirector Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(OnlyOneDirector.Match match) {
				System.out.printf("[REM OnlyOneDirector Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<OnlyOneRnDManager.Match> getOnlyOneRnDManagerUpdateListener() {
		return new IMatchUpdateListener<OnlyOneRnDManager.Match>() {
			@Override
			public void notifyAppearance(OnlyOneRnDManager.Match match) {
				System.out.printf("[ADD OnlyOneRnDManager Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(OnlyOneRnDManager.Match match) {
				System.out.printf("[REM OnlyOneRnDManager Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<OnlyOneOperationsManager.Match> getOnlyOneOperationsManagerUpdateListener() {
		return new IMatchUpdateListener<OnlyOneOperationsManager.Match>() {
			@Override
			public void notifyAppearance(OnlyOneOperationsManager.Match match) {
				System.out.printf("[ADD OnlyOneOperationsManager Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(OnlyOneOperationsManager.Match match) {
				System.out.printf("[REM OnlyOneOperationsManager Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<SoDEmployeeAndContractor.Match> getSoDEmployeeAndContractorUpdateListener() {
		return new IMatchUpdateListener<SoDEmployeeAndContractor.Match>() {
			@Override
			public void notifyAppearance(SoDEmployeeAndContractor.Match match) {
				System.out.printf("[ADD SoDEmployeeAndContractor Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(SoDEmployeeAndContractor.Match match) {
				System.out.printf("[REM SoDEmployeeAndContractor Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<SoDEmployeeAndVisitor.Match> getSoDEmployeeAndVisitorUpdateListener() {
		return new IMatchUpdateListener<SoDEmployeeAndVisitor.Match>() {
			@Override
			public void notifyAppearance(SoDEmployeeAndVisitor.Match match) {
				System.out.printf("[ADD SoDEmployeeAndVisitor Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(SoDEmployeeAndVisitor.Match match) {
				System.out.printf("[REM SoDEmployeeAndVisitor Match] %s %n", match.prettyPrint());

			}
		};
	}

//	public static IMatchUpdateListener<PrerequisiteEverybodyHasAccessToLobby.Match> getPrerequisiteEveryHasAccessToLobbyUpdateListener() {
//		return new IMatchUpdateListener<PrerequisiteEverybodyHasAccessToLobby.Match>() {
//			@Override
//			public void notifyAppearance(PrerequisiteEverybodyHasAccessToLobby.Match match) {
//				System.out.printf("[ADD PrerequisiteEverybodyHasAccessToLobby Match] %s %n", match.prettyPrint());
//			}
//
//			@Override
//			public void notifyDisappearance(PrerequisiteEverybodyHasAccessToLobby.Match match) {
//				System.out.printf("[REM PrerequisiteEverybodyHasAccessToLobby Match] %s %n", match.prettyPrint());
//
//			}
//		};
//	}
//
//	public static IMatchUpdateListener<PrerequisiteVaultImpliesOpenOffice.Match> getPrerequisiteVaultImpliesOpenOfficeUpdateListener() {
//		return new IMatchUpdateListener<PrerequisiteVaultImpliesOpenOffice.Match>() {
//			@Override
//			public void notifyAppearance(PrerequisiteVaultImpliesOpenOffice.Match match) {
//				System.out.printf("[ADD PrerequisiteVaultImpliesOpenOffice Match] %s %n", match.prettyPrint());
//			}
//
//			@Override
//			public void notifyDisappearance(PrerequisiteVaultImpliesOpenOffice.Match match) {
//				System.out.printf("[REM PrerequisiteVaultImpliesOpenOffice Match] %s %n", match.prettyPrint());
//
//			}
//		};
//	}

	public static IMatchUpdateListener<AccessRelation.Match> getAccessRelationUpdateListener() {
		return new IMatchUpdateListener<AccessRelation.Match>() {
			@Override
			public void notifyAppearance(AccessRelation.Match match) {
				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
				String userName = match.getUser().getName();
				String permissionName = match.getPermission().getName();
				System.out.println("[ADD AccessRelation Match] " + userName + " has permission " + permissionName + " during " + groupNames);
			}

			@Override
			public void notifyDisappearance(AccessRelation.Match match) {
				System.out.printf("[REM AccessRelation Match] %s %n", match.prettyPrint());

			}
		};
	}
	
//	public static IMatchUpdateListener<AccessRelationWithHierarchies.Match> getAccessRelationWithHierarchiesUpdateListener() {
//		return new IMatchUpdateListener<AccessRelationWithHierarchies.Match>() {
//			@Override
//			public void notifyAppearance(AccessRelationWithHierarchies.Match match) {
//				System.out.printf("[ADD AccessRelation2 Match] %s %n", match.prettyPrint());
//			}
//
//			@Override
//			public void notifyDisappearance(AccessRelationWithHierarchies.Match match) {
//				System.out.printf("[REM AccessRelation2 Match] %s %n", match.prettyPrint());
//
//			}
//		};
//	}
	
	public static IMatchUpdateListener<AllJuniors.Match> getAllJuniorsUpdateListener() {
		return new IMatchUpdateListener<AllJuniors.Match>() {
			@Override
			public void notifyAppearance(AllJuniors.Match match) {
				System.out.printf("[ADD AllJuniors Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(AllJuniors.Match match) {
				System.out.printf("[REM AllJuniors Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<RangeP.Match> getRangeUpdateListener() {
		return new IMatchUpdateListener<RangeP.Match>() {
			@Override
			public void notifyAppearance(RangeP.Match match) {
				//System.out.printf("[ADD Range Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(RangeP.Match match) {
				//System.out.printf("[REM Range Match] %s %n", match.prettyPrint());

			}
		};
	}

//	public static IMatchUpdateListener<TimeRangeGroupCollection.Match> getTimeRangeGroupCombinationsUpdateListener() {
//		return new IMatchUpdateListener<TimeRangeGroupCollection.Match>() {
//			@Override
//			public void notifyAppearance(TimeRangeGroupCollection.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				System.out.printf("[ADD TimeRangeGroupCombinations Match] %s %n", groupNames);
//			}
//
//			@Override
//			public void notifyDisappearance(TimeRangeGroupCollection.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				System.out.printf("[REM TimeRangeGroupCombinations Match] %s %n", groupNames);
//			}
//		};
//	}
//
//	public static IMatchUpdateListener<TimeRangeGroupCollectionHasGroup.Match> getTimeRangeGroupCollectionHasGroupUpdateListener() {
//		return new IMatchUpdateListener<TimeRangeGroupCollectionHasGroup.Match>() {
//			@Override
//			public void notifyAppearance(TimeRangeGroupCollectionHasGroup.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				String groupName = match.getGroup().getName();
//				System.out.println("[ADD TimeRangeGroupCollectionHasGroup Match]" + groupNames + " has " + groupName);
//			}
//
//			@Override
//			public void notifyDisappearance(TimeRangeGroupCollectionHasGroup.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				String groupName = match.getGroup().getName();
//				System.out.println("[ADD TimeRangeGroupCollectionHasGroup Match]" + groupNames + " has " + groupName);
//			}
//		};
//	}
//
	public static IMatchUpdateListener<TimeRangeGroupCollectionEnabled.Match> getTimeRangeGroupCollectionEnabledUpdateListener() {
		return new IMatchUpdateListener<TimeRangeGroupCollectionEnabled.Match>() {
			@Override
			public void notifyAppearance(TimeRangeGroupCollectionEnabled.Match match) {
				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
				String roleName = match.getRole().getName();
				String demarcationName = match.getDemarcation().getName();
				System.out.println("[ADD TimeRangeGroupCollectionEnabled Match] " + groupNames + " -> " + roleName + "-" + demarcationName);
			}

			@Override
			public void notifyDisappearance(TimeRangeGroupCollectionEnabled.Match match) {
				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
				String roleName = match.getRole().getName();
				String demarcationName = match.getDemarcation().getName();
				System.out.println("[ADD TimeRangeGroupCollectionEnabled Match]" + groupNames + " -> " + roleName + "-" + demarcationName);
			}
		};
	}
//
//	public static IMatchUpdateListener<EnabledPriority.Match> getEnabledPriorityUpdateListener() {
//		return new IMatchUpdateListener<EnabledPriority.Match>() {
//			@Override
//			public void notifyAppearance(EnabledPriority.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				String roleName = match.getRole().getName();
//				String demarcationName = match.getDemarcation().getName();
//				System.out.println("[ADD EnabledPriority Match]" + groupNames + " -> " + roleName + "-" + demarcationName);
//			}
//
//			@Override
//			public void notifyDisappearance(EnabledPriority.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				String roleName = match.getRole().getName();
//				String demarcationName = match.getDemarcation().getName();
//				System.out.println("[ADD EnabledPriority Match]" + groupNames + " -> " + roleName + "-" + demarcationName);
//			}
//		};
//	}
//
//	public static IMatchUpdateListener<DisabledPriority.Match> getDisabledPriorityUpdateListener() {
//		return new IMatchUpdateListener<DisabledPriority.Match>() {
//			@Override
//			public void notifyAppearance(DisabledPriority.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				String roleName = match.getRole().getName();
//				String demarcationName = match.getDemarcation().getName();
//				System.out.println("[ADD DisabledPriority Match]" + groupNames + " -> " + roleName + "-" + demarcationName);
//			}
//
//			@Override
//			public void notifyDisappearance(DisabledPriority.Match match) {
//				Set<TimeRangeGroup> groups = (Set<TimeRangeGroup>) match.getGroups();
//				Set<String> groupNames = groups.stream().map(x -> x.getName()).collect(Collectors.toSet());
//				String roleName = match.getRole().getName();
//				String demarcationName = match.getDemarcation().getName();
//				System.out.println("[ADD DisabledPriority Match]" + groupNames + " -> " + roleName + "-" + demarcationName);
//			}
//		};
//	}

}
