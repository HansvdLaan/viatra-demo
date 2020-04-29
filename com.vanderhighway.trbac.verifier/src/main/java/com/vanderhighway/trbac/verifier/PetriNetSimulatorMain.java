/*******************************************************************************
 * Copyright (c) 2010-2015, Zoltan Ujhelyi, Gabor Szarnyas
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-v20.html.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package com.vanderhighway.trbac.verifier;


import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import com.vanderhighway.trbac.model.trbac.model.PetriNetPackage;

public class PetriNetSimulatorMain {
	public static void main(String[] args) throws IOException {

		//Debug output
		//BasicConfigurator.configure();

		// Initializing the EMF package
		PetriNetPackage.eINSTANCE.getName();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("petrinet", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put("*", new XMIResourceFactoryImpl());

		ResourceSet set = new ResourceSetImpl();
		URI uri = URI.createFileURI("My.trbac");
		set.getResource(uri, true);

		final AdvancedViatraQueryEngine engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(set));

		PetriNetSimulator simulator = new PetriNetSimulator(engine);
		simulator.fire(10);
	}
}
