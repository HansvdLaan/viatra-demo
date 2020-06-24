package com.vanderhighway.trbac.verifier.modifier;

import com.brein.time.timeintervals.indexes.IntervalTree;
import com.brein.time.timeintervals.intervals.IntegerInterval;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
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
    }

    //TODO: move this to somewhere else!
    public void addChangeListeners(AdvancedViatraQueryEngine engine) {
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
        //engine.addMatchUpdateListener(RoleName.Matcher.on(engine), ListenerFactory.getRoleNameMatchUpdateListener(), true);

        //engine.addMatchUpdateListener(MissingInheritedDemarcation.Matcher.on(engine), ListenerFactory.getMissingInheritedDemarcationUpdateListener(), true);
        //engine.addMatchUpdateListener(InheritedDemarcation.Matcher.on(engine), ListenerFactory.getInheritedDemarcationUpdateListener(), true);

        // Add Access Relation Listener
        //engine.addMatchUpdateListener(AllJuniors.Matcher.on(engine), ListenerFactory.getAllJuniorsUpdateListener(), true);
        //engine.addMatchUpdateListener(AccessRelation.Matcher.on(engine), ListenerFactory.getAccessRelationUpdateListener(), true);
        //engine.addMatchUpdateListener(AccessRelation2.Matcher.on(engine), ListenerFactory.getAccessRelation2UpdateListener(), true);
//        engine.addMatchUpdateListener(DayRange.Matcher.on(engine), ListenerFactory.getDayRangeUpdateListener(), true);
//        engine.addMatchUpdateListener(NonOverlappingDayRange.Matcher.on(engine), ListenerFactory.getNonOverlappingDayRangeUpdateListener(), true);
        //engine.addMatchUpdateListener(RangeP.Matcher.on(engine), ListenerFactory.getRangeUpdateListener(), true);
        //engine.addMatchUpdateListener(ScheduleRangeP.Matcher.on(engine), ListenerFactory.getScheduleRangeUpdateListener(), true);
        //engine.addMatchUpdateListener(TimeRange.Matcher.on(engine), ListenerFactory.getTimeRangeUpdateListener(), true);
        //engine.addMatchUpdateListener(SetTestQuery.Matcher.on(engine), ListenerFactory.getSetTestQueryUpdateListener(), true);
        //engine.addMatchUpdateListener(TimeRangeGroupsDistinct.Matcher.on(engine), ListenerFactory.getTimeRangeGroupDistinctUpdateListener(), true);
        //engine.addMatchUpdateListener(DistinctGroups.Matcher.on(engine), ListenerFactory.getDistinctGroupsUpdateListener(), true);

        //engine.addMatchUpdateListener(AllJuniors.Matcher.on(engine), ListenerFactory.getAllJuniorsUpdateListener(), true);
        //engine.addMatchUpdateListener(TimeRangeGroupCollection.Matcher.on(engine), ListenerFactory.getTimeRangeGroupCombinationsUpdateListener(), true);
        //engine.addMatchUpdateListener(TimeRangeGroupCollectionHasGroup.Matcher.on(engine), ListenerFactory.getTimeRangeGroupCollectionHasGroupUpdateListener(), true);
        //engine.addMatchUpdateListener(TimeRangeGroupCollectionEnabled.Matcher.on(engine), ListenerFactory.getTimeRangeGroupCollectionEnabledUpdateListener(), true);

        //engine.addMatchUpdateListener(EnabledPriority.Matcher.on(engine), ListenerFactory.getEnabledPriorityUpdateListener(), true);
        //engine.addMatchUpdateListener(DisabledPriority.Matcher.on(engine), ListenerFactory.getDisabledPriorityUpdateListener(), true);
        //engine.addMatchUpdateListener(AllJuniors.Matcher.on(engine), ListenerFactory.getAllJuniorsUpdateListener(), true);

        //engine.addMatchUpdateListener(TimeRangeGroup.Matcher.on(engine), ListenerFactory.getTimeRangeGroupUpdateListener(), true);
    }

}
