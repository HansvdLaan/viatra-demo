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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class EmptyPolicyGenerator {

	@Extension
	private static TRBACPackage ePackage = TRBACPackage.eINSTANCE;

	public static void main(String[] args) throws IOException, InvocationTargetException, ModelManipulationException, ModelManipulationException, ParseException {

		String fileSeparator = System.getProperty("file.separator");

		System.out.println("Empty Policy Generator Called!");
		System.out.println("Initialize model scope and preparing engine... ");

		// Initializing the EMF package
		TRBACPackage.eINSTANCE.getName();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("trbac", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put("*", new XMIResourceFactoryImpl());

		ResourceSet set = new ResourceSetImpl();

		String relativePath = "."+fileSeparator+"empty_policy_all_schedules.trbac";
		File file = new File(relativePath);
		if(file.createNewFile()){
			System.out.println(relativePath+" File Created in Project root directory");
		}
		else System.out.println("File "+relativePath+" already exists in the project root directory");
		URI uri = URI.createFileURI("./empty_policy_all_schedules.trbac");
		Resource resource = set.createResource(uri);

		resource.getContents().add(EcoreUtil.create(ePackage.getPolicy()));
		Policy policy = ((Policy) resource.getContents().get(0));
		policy.setName("DummyPolicy");

		Schedule schedule = (Schedule) EcoreUtil.create(ePackage.getSchedule());
		schedule.setName("DummySchedule");
		policy.setSchedule(schedule);

		final AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(set));
		PolicyModifier modifier = new PolicyModifier(engine, (Policy) resource.getContents().get(0), resource);

		Map<String, DayOfWeekSchedule> dayOfWeekScheduleMap = new HashMap<>();
		Map<String, Map<Integer, DayOfMonthSchedule>> dayOfMonthScheduleMap = new HashMap();

		TimeRangeGroup always = modifier.addTimeRangeGroup("Always");
		List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
		for (String day: days) {
			DayOfWeekSchedule dayOfWeekSchedule = modifier.addDayOfWeekSchedule(day);
			TimeRange alwaysRange = modifier.addTimeRange(always, dayOfWeekSchedule, "Always_" + day, new IntegerInterval(0,1439));

			DayScheduleTimeRange alwaysScheduleRange = modifier.addDayScheduleTimeRange(dayOfWeekSchedule, "" +
							"Always_" + day + "_DSTR", new IntegerInterval(0,1439));

			modifier.getManipulation().addTo(alwaysRange, ePackage.getTimeRange_DayScheduleTimeRanges(), alwaysScheduleRange);

			dayOfWeekScheduleMap.put(day, dayOfWeekSchedule);
		}

		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December");
		List<Integer> monthDays = Arrays.asList(31,29,31,30,31,30,31,31,30,31,30,31);
		for (int monthIndex = 0; monthIndex < months.size(); monthIndex++) {
			dayOfMonthScheduleMap.put(months.get(monthIndex), new HashMap<>());
			for (int dayIndex = 0; dayIndex < monthDays.get(monthIndex); dayIndex++) {
				String monthDay = dayIndex + "_" + months.get(monthIndex);
				DayOfMonthSchedule dayOfMonthSchedule = modifier.addDayOfMonthSchedule(monthDay);
				TimeRange alwaysRange = modifier.addTimeRange(always, dayOfMonthSchedule, "Always_" + monthDay,
						new IntegerInterval(0, 1439));

				DayScheduleTimeRange alwaysScheduleRange = modifier.addDayScheduleTimeRange(dayOfMonthSchedule, "" +
						"Always_" + monthDay + "_DSTR", new IntegerInterval(0,1439));

				modifier.getManipulation().addTo(alwaysRange, ePackage.getTimeRange_DayScheduleTimeRanges(), alwaysScheduleRange);

				dayOfMonthScheduleMap.get(months.get(monthIndex)).put(dayIndex, dayOfMonthSchedule);
			}
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse("2020-01-01");
		Date endDate = formatter.parse("2030-01-01");

		LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
			// Do your job here with `date`.

			DayOfWeekSchedule weekSchedule = dayOfWeekScheduleMap.get(days.get(date.getDayOfWeek().getValue()-1));
			DayOfMonthSchedule monthSchedule = dayOfMonthScheduleMap.get(months.get(date.getMonthValue() - 1)).get(date.getDayOfMonth() - 1);

			String name = weekSchedule.getName() + "_" + monthSchedule.getName() + "_" + date.getYear();
			DayOfYearSchedule yearSchedule = modifier.addDayOfYearSchedule(weekSchedule, monthSchedule, name);

			TimeRange alwaysRange = modifier.addTimeRange(always, yearSchedule, "Always_" + name,
					new IntegerInterval(0, 1439));

			DayScheduleTimeRange alwaysScheduleRange = modifier.addDayScheduleTimeRange(yearSchedule, "" +
					"Always_" + name + "_DSTR", new IntegerInterval(0,1439));

			modifier.getManipulation().addTo(alwaysRange, ePackage.getTimeRange_DayScheduleTimeRanges(), alwaysScheduleRange);
		}

		resource.save(Collections.emptyMap());

		modifier.dispose();

		System.out.println("Done!");
	}
}
