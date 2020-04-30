package com.vanderhighway.trbac.verifier;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.viatra.query.runtime.api.IMatchUpdateListener;
import com.vanderhighway.trbac.verifier.UserShouldHaveARole;

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
		updateListeners.add(getPrerequisiteEveryHasAccessToLobbyUpdateListener());
		updateListeners.add(getPrerequisiteVaultImpliesOpenOfficeUpdateListener());
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

	public static IMatchUpdateListener<PrerequisiteEverybodyHasAccessToLobby.Match> getPrerequisiteEveryHasAccessToLobbyUpdateListener() {
		return new IMatchUpdateListener<PrerequisiteEverybodyHasAccessToLobby.Match>() {
			@Override
			public void notifyAppearance(PrerequisiteEverybodyHasAccessToLobby.Match match) {
				System.out.printf("[ADD PrerequisiteEverybodyHasAccessToLobby Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(PrerequisiteEverybodyHasAccessToLobby.Match match) {
				System.out.printf("[REM PrerequisiteEverybodyHasAccessToLobby Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<PrerequisiteVaultImpliesOpenOffice.Match> getPrerequisiteVaultImpliesOpenOfficeUpdateListener() {
		return new IMatchUpdateListener<PrerequisiteVaultImpliesOpenOffice.Match>() {
			@Override
			public void notifyAppearance(PrerequisiteVaultImpliesOpenOffice.Match match) {
				System.out.printf("[ADD PrerequisiteVaultImpliesOpenOffice Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(PrerequisiteVaultImpliesOpenOffice.Match match) {
				System.out.printf("[REM PrerequisiteVaultImpliesOpenOffice Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<AccessRelation.Match> getAccessRelationUpdateListener() {
		return new IMatchUpdateListener<AccessRelation.Match>() {
			@Override
			public void notifyAppearance(AccessRelation.Match match) {
				System.out.printf("[ADD AccessRelation Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(AccessRelation.Match match) {
				System.out.printf("[REM AccessRelation Match] %s %n", match.prettyPrint());

			}
		};
	}

	public static IMatchUpdateListener<MissingInheritedDemarcation.Match> getMissingInheritedDemarcationUpdateListener() {
		return new IMatchUpdateListener<MissingInheritedDemarcation.Match>() {
			@Override
			public void notifyAppearance(MissingInheritedDemarcation.Match match) {
				System.out.printf("[ADD MissingInheritedDemarcation Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(MissingInheritedDemarcation.Match match) {
				System.out.printf("[REM MissingInheritedDemarcation Match] %s %n", match.prettyPrint());

			}
		};
	}
	
	public static IMatchUpdateListener<InheritedDemarcation.Match> getInheritedDemarcationUpdateListener() {
		return new IMatchUpdateListener<InheritedDemarcation.Match>() {
			@Override
			public void notifyAppearance(InheritedDemarcation.Match match) {
				System.out.printf("[ADD InheritedDemarcation Match] %s %n", match.prettyPrint());
			}

			@Override
			public void notifyDisappearance(InheritedDemarcation.Match match) {
				System.out.printf("[REM InheritedDemarcation Match] %s %n", match.prettyPrint());

			}
		};
	}
}
