/*******************************************************************************
 * Copyright (c) 2010-2015, Zoltan Ujhelyi, Gabor Szarnyas
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-v20.html.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

import com.google.common.reflect.TypeToken;
import com.vanderhighway.trbac.core.modifier.PolicyModifier;
import com.vanderhighway.trbac.core.validator.PolicyValidator;
import com.vanderhighway.trbac.model.trbac.model.Policy;
import com.vanderhighway.trbac.model.trbac.model.TRBACPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.transformation.runtime.emf.modelmanipulation.ModelManipulationException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class PolicyValidatorMain {
	public static final Type test = new TypeToken<Set<Integer>>(){}.getType();
	public static final String val0 = "A";

	public static void main(String[] args) throws IOException, InvocationTargetException, ModelManipulationException {

		//Debug output
		//BasicConfigurator.configure();

		System.out.println("Validator Called!");
		System.out.print("Initialize model scope and preparing engine... ");

		// Initializing the EMF package
		TRBACPackage.eINSTANCE.getName();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("trbac", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put("*", new XMIResourceFactoryImpl());

		LocalDateTime now = LocalDateTime.now();

		ResourceSet set = new ResourceSetImpl();
		//URI uri = URI.createFileURI("models/basic/intervals.trbac");
		URI uri = URI.createFileURI("./My.trbac");
		Resource resource = set.getResource(uri, true);

		LocalDateTime now2 =  LocalDateTime.now();
		System.out.println(now.until(now2, ChronoUnit.SECONDS));

		final AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(set));

		LocalDateTime now3 =  LocalDateTime.now();
		System.out.println(now2.until(now3, ChronoUnit.SECONDS));


		PolicyModifier modifier = new PolicyModifier(engine, (Policy) resource.getContents().get(0), resource);
		PolicyValidator validator = new PolicyValidator(engine);

		validator.addChangeListeners(engine);

		LocalDateTime now4 =  LocalDateTime.now();
		System.out.println(now3.until(now4, ChronoUnit.SECONDS));

		modifier.dispose();

		System.out.println("Done!");
	}
}
