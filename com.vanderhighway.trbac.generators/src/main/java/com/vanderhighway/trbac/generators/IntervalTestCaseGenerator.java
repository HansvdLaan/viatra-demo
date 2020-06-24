package com.vanderhighway.trbac.generators;

import com.brein.time.timeintervals.intervals.IntegerInterval;
import com.vanderhighway.trbac.core.modifier.PolicyModifier;
import com.vanderhighway.trbac.model.trbac.model.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.ModelManipulationException;
import org.eclipse.xtext.xbase.lib.Extension;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class IntervalTestCaseGenerator {

    @Extension
    private static TRBACPackage ePackage = TRBACPackage.eINSTANCE;

    public static void main(String[] args) throws IOException, InvocationTargetException, ModelManipulationException, ModelManipulationException, ParseException {



    }

    public static void generateBasicAddRange() throws IOException, ModelManipulationException {
        String fileSeparator = System.getProperty("file.separator");

        System.out.println("Policy Interval Test Case Generator Called!");
        System.out.print("Initialize model scope and preparing engine... ");

        // Initializing the EMF package
        TRBACPackage.eINSTANCE.getName();
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("trbac", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put("*", new XMIResourceFactoryImpl());

        ResourceSet set = new ResourceSetImpl();

        String relativePath = "." + fileSeparator + ".trbac";
        File file = new File(relativePath);
        if (file.createNewFile()) {
            System.out.println(relativePath + " File Created in Project root directory");
        } else System.out.println("File " + relativePath + " already exists in the project root directory");
        URI uri = URI.createFileURI("./basic_add_range.trbac");
        Resource resource = set.createResource(uri);

        resource.getContents().add(EcoreUtil.create(ePackage.getPolicy()));
        Policy policy = ((Policy) resource.getContents().get(0));
        policy.setName("DummyPolicy");

        Schedule schedule = (Schedule) EcoreUtil.create(ePackage.getSchedule());
        schedule.setName("DummySchedule");
        policy.setSchedule(schedule);

        final AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(set));
        PolicyModifier modifier = new PolicyModifier(engine, (Policy) resource.getContents().get(0), resource);

        DayOfWeekSchedule monday = modifier.addDayOfWeekSchedule("Monday");

        TimeRangeGroup always = modifier.addTimeRangeGroup("Always");
        TimeRange alwaysRange = modifier.addTimeRange(always, monday, "Always-Monday", new IntegerInterval(0,1439));

        TimeRangeGroup testRanges = modifier.addTimeRangeGroup("TestRanges");
        TimeRange tr1 = modifier.addTimeRange(testRanges, monday, "TR1", new IntegerInterval(0,2));
        TimeRange tr2 = modifier.addTimeRange(testRanges, monday, "TR2", new IntegerInterval(5,5));
        TimeRange tr3 = modifier.addTimeRange(testRanges, monday, "TR3", new IntegerInterval(1,2));
        TimeRange tr4 = modifier.addTimeRange(testRanges, monday, "TR4", new IntegerInterval(2,3));
        TimeRange tr5 = modifier.addTimeRange(testRanges, monday, "TR5", new IntegerInterval(0,4));
        TimeRange tr6 = modifier.addTimeRange(testRanges, monday, "TR6", new IntegerInterval(0,5));
        TimeRange tr7 = modifier.addTimeRange(testRanges, monday, "TR7", new IntegerInterval(30,35));

    }
}
