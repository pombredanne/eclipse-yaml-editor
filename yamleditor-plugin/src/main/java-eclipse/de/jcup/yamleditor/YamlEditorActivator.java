/*
 * Copyright 2017 Albert Tregnaghi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 */
package de.jcup.yamleditor;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.jcup.eclipse.commons.TodoTasksSupport;

/**
 * The activator class controls the plug-in life cycle
 */
public class YamlEditorActivator extends AbstractUIPlugin{

	// The plug-in COMMAND_ID
	public static final String PLUGIN_ID = "de.jcup.yamleditor"; //$NON-NLS-1$

	// The shared instance
	private static YamlEditorActivator plugin;
	private ColorManager colorManager;

	private TodoTasksSupport taskSupport;

	/**
	 * The constructor
	 */
	public YamlEditorActivator() {
		colorManager = new ColorManager();
		taskSupport = new TodoTasksSupport(new YamlTodoTaskSupportProvider());
	}

	public ColorManager getColorManager() {
		return colorManager;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		taskSupport.install();
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		colorManager.dispose();
		taskSupport.uninstall();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static YamlEditorActivator getDefault() {
		return plugin;
	}

}
