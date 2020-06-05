package com.vanderhighway.trbac.verifier;

import com.google.common.base.Objects;
import com.vanderhighway.trbac.model.trbac.model.TRBACPackage;
import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.transformation.evm.specific.Lifecycles;
import org.eclipse.viatra.transformation.evm.specific.crud.CRUDActivationStateEnum;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.IModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.SimpleModelManipulations;
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.eventdriven.EventDrivenTransformation;
import org.eclipse.xtext.xbase.lib.Extension;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class PolicyAutomaticModifier {

    @Extension
    private Logger logger = Logger.getLogger(PolicyValidator.class);

    @Extension
    private IModelManipulations manipulation;

    @Extension
    private TRBACPackage ePackage = TRBACPackage.eINSTANCE;

    @Extension //Transformation-related extensions
    private EventDrivenTransformation transformation;

    @Extension //Transformation rule-related extensions
    private EventDrivenTransformationRuleFactory _eventDrivenTransformationRuleFactory = new EventDrivenTransformationRuleFactory();

    private AdvancedViatraQueryEngine engine;
    private PolicyModifier policyModifier;

    public PolicyAutomaticModifier(AdvancedViatraQueryEngine engine, PolicyModifier policyModifier) {
        this.engine = engine;
        this.policyModifier = policyModifier;

       /* this.tree = IntervalTreeBuilder.newBuilder()
                .usePredefinedType(IntervalTreeBuilder.IntervalType.LONG)
                .collectIntervals(interval -> new ListIntervalCollection())
                .build();
        this.tree.insert(new IntegerInterval(0,3599));
        this.intervalmap = new HashMap<>();*/

    }

    public void initialize() {
        this.logger.info("Preparing transformation rules.");
        this.transformation = createTransformation();
        this.logger.info("Prepared transformation rules");
    }

    public void execute() {
        this.logger.debug("Executing transformations");
        this.transformation.getExecutionSchema().startUnscheduledExecution();
    }

    private EventDrivenTransformation createTransformation() {
        EventDrivenTransformation transformation = null;
        this.manipulation = new SimpleModelManipulations(this.engine);
        transformation = EventDrivenTransformation.forEngine(this.engine)
                .addRule(this.ProcessRanges())
                .addRule(this.ProcessRoleNameMatches())
                .build();
        return transformation;
    }

    public void dispose() {
        if (!Objects.equal(this.transformation, null)) {
            this.transformation.dispose();
        }
        this.transformation = null;
        return;
    }

    public class DoAddScheduleRange implements Callable<Void> {
        private final PolicyModifier modifier;
        private final Range.Match it;

        public DoAddScheduleRange(PolicyModifier modifier, Range.Match it) {
            this.modifier = modifier;
            this.it = it;
        }

        public Void call() throws Exception {
            String key = String.valueOf("copy-" + it.getRange().toString());
            this.modifier.execute(this.modifier.addScheduleRange(it.getRange().getDayschedule(),
                    key, it.getStarttime(), it.getEndtime()));
            return null;
        }
    }

    private EventDrivenTransformationRule<Range.Match, Range.Matcher> ProcessRanges() {
        final Consumer<Range.Match> function = (Range.Match it) -> {
            System.out.println("hey!");
        };
        EventDrivenTransformationRule<Range.Match, Range.Matcher> dayrangerule =
                this._eventDrivenTransformationRuleFactory.createRule(Range.instance()).action(
                        CRUDActivationStateEnum.CREATED, (Range.Match it) -> {
                            System.out.println("DayRangeMatch CREATED:" + it.toString());
                            try {
                                engine.delayUpdatePropagation(new DoAddScheduleRange(this.policyModifier, it));
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }).action(
                        CRUDActivationStateEnum.UPDATED, (Range.Match it) -> {
                            System.out.println("DayRangeMatch UPDATED:" + it.toString());}).action(
                        CRUDActivationStateEnum.DELETED, (Range.Match it) -> {
                            System.out.println("DayRangeMatch DELETED:" + it.toString());}
                            ).addLifeCycle(Lifecycles.getDefault(true, true))
                        .name("process-day-ranges").build();
        return dayrangerule;
    }

    private EventDrivenTransformationRule<RoleName.Match, RoleName.Matcher> ProcessRoleNameMatches() {
        final Consumer<RoleName.Match> function = (RoleName.Match it) -> {
            System.out.println("hey!");
        };
        EventDrivenTransformationRule<RoleName.Match, RoleName.Matcher> test =
                this._eventDrivenTransformationRuleFactory.createRule(RoleName.instance()).action(
                        CRUDActivationStateEnum.CREATED, (RoleName.Match it) -> {
                            System.out.println("RoleName CREATED:" + it.toString());}).action(
                        CRUDActivationStateEnum.UPDATED, (RoleName.Match it) -> {
                            System.out.println("RoleName UPDATED:" + it.toString());}).action(
                        CRUDActivationStateEnum.DELETED, (RoleName.Match it) -> {
                            System.out.println("RoleName DELETED:" + it.toString());}
                ).addLifeCycle(Lifecycles.getDefault(true, true))
                        .name("process-day-ranges").build();
        return test;
    }

//    private EventDrivenTransformationRule<DayRange.Match, DayRange.Matcher> ProcessDayRanges() {
//        final Consumer<DayRange.Match> function = (DayRange.Match it) -> {
//            System.out.println("hey!");
//            final Consumer<Policy.Match> function2 = (Policy.Match it2) -> {
//                try {
                    //EObject entity = this.manipulation.createChild(it2.getPolicy(), ePackage.getPolicy_Roles(), ePackage.getRole());
                    //this.manipulation.set(entity, ePackage.getRole_Name(), it.getRange().getName() + " copy");
        //}

//        final EventDrivenTransformationRule<DayRange.Match, DayRange.Matcher> exampleRule =
//                this._eventDrivenTransformationRuleFactory.createRule(DayRange.instance()).action(
//                        CRUDActivationStateEnum.CREATED,  (DayRange.Match it) -> {
//                        }).action(
//                        CRUDActivationStateEnum.UPDATED, (DayRange.Match it) -> {
//                        }).action(
//                        CRUDActivationStateEnum.DELETED, (DayRange.Match it) -> {
//                        }).addLifeCycle(Lifecycles.getDefault(true, true)).build();
//        return exampleRule;
//    }


}
