/*******************************************************************************
 * Copyright (c) 2010-2015, Zoltan Ujhelyi, Gabor Szarnyas
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-v20.html.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package com.vanderhighway.trbac.verifier;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import com.vanderhighway.trbac.model.trbac.model.PetriNetPackage;
import com.vanderhighway.trbac.verifier.PolicyValidator;

public class PolicyValidatorMain {
	public static void main(String[] args) throws IOException {

		//Debug output
		//BasicConfigurator.configure();

		System.out.println("Validator Called!");
		System.out.print("Initialize model scope and preparing engine... ");
		
		// Initializing the EMF package
		PetriNetPackage.eINSTANCE.getName();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("petrinet", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put("*", new XMIResourceFactoryImpl());

		ResourceSet set = new ResourceSetImpl();
		URI uri = URI.createFileURI("models/basic/role_inheritance.trbac");
		//URI uri = URI.createFileURI("com.vanderhighway.trbac.verifier/models/basic/role_inheritance.trbac");
		set.getResource(uri, true);

		final AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(set));

		PolicyValidator validator = new PolicyValidator(engine);
		PolicyModifier modifier = new PolicyModifier(engine);

		validator.initialize();
		validator.execute();
		validator.dispose();

		modifier.execute(modifier.addRole("R4"));
		modifier.execute(modifier.addRoleInheritance("R1", "R4"));
		modifier.dispose();

		System.out.println("Done!");
	}
}
