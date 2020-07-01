package com.vanderhighway.trbac.core.validator;

import com.vanderhighway.trbac.patterns.*;
import org.eclipse.viatra.query.runtime.api.IMatchUpdateListener;

import java.util.HashSet;
import java.util.Set;

public class ListenerFactory {

	public static Set<IMatchUpdateListener> getALlUpdateListeners() {
		Set<IMatchUpdateListener> updateListeners = new HashSet();
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


}
