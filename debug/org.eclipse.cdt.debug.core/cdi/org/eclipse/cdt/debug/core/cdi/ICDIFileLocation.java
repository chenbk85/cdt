/*******************************************************************************
 * Copyright (c) 2000, 2005 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     QNX Software Systems - Initial API and implementation
 *******************************************************************************/

package org.eclipse.cdt.debug.core.cdi;

public interface ICDIFileLocation extends ICDILocation {

	/**
	 * Returns the source file of this location or <code>null</code>
	 * if the source file is unknown.
	 *  
	 * @return the source file of this location
	 */
	String getFile();

}
