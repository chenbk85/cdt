/*******************************************************************************
 * Copyright (c) 2012, 2013 Tilera Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     William R. Swanson (Tilera Corporation) - initial API and implementation
 *     Marc Dumais (Ericsson) - Add CPU/core load information to the multicore visualizer (Bug 396268)
 *     Marc Dumais (Ericsson) -  Bug 405390
 *******************************************************************************/

package org.eclipse.cdt.dsf.gdb.multicorevisualizer.internal.ui.model;


/** Represents single core of a CPU. */
public class VisualizerCore
	implements Comparable<VisualizerCore>, IVisualizerModelObject
{
	// --- members ---
	
	/** CPU this core is part of. */
	public VisualizerCPU m_cpu = null;
	
	/** Linux CPU ID of this core. */
	public int m_id = 0;
	
	/** Contains load information
	 * @since 1.1
	 */
	protected VisualizerLoadInfo m_loadinfo;
	
	// --- constructors/destructors ---
	
	/** Constructor */
	public VisualizerCore(VisualizerCPU cpu, int id) {
		m_cpu = cpu;
		m_id = id;
	}
	
	/** Dispose method */
	public void dispose() {
		m_loadinfo = null;
	}
	
	
	// --- Object methods ---
	
	/** Returns string representation. */
	@Override
	public String toString() {
		return m_cpu + ",Core:" + m_id; //$NON-NLS-1$
	}

	
	// --- accessors ---
	
	/** Gets CPU this core is part of. */
	public VisualizerCPU getCPU() {
		return m_cpu;
	}

	/** Gets Linux CPU ID of this core. */
	@Override
	public int getID() {
		return m_id;
	}
	
	/**  Return CPU this core is on. */
	@Override
	public IVisualizerModelObject getParent() {
		return getCPU();
	}
	
	/** sets the load info for this core 
	 * @since 1.1*/
	public synchronized void setLoadInfo (VisualizerLoadInfo info) {
		m_loadinfo = info;
	}
	
	/** Gets the CPU usage load of this core. 
	 * @since 1.1*/
	public synchronized Integer getLoad() {
		return (m_loadinfo == null) ? null : m_loadinfo.getLoad();
	}
	
	/** get the highest recorded load for this core
	 * @since 1.1*/
	public synchronized Integer getHighLoadWatermark() {
		return (m_loadinfo == null) ? null : m_loadinfo.getHighLoadWaterMark();
	}

	
	// --- methods ---
	
	
	
	// --- Comparable implementation ---
	
	/** Compares this item to the specified item. */
	@Override
	public int compareTo(VisualizerCore o) {
		int result = 0;
		if (o != null) {
			if (m_id < o.m_id) {
				result = -1;
			}
			else if (m_id > o.m_id) {
				result = 1;
			}
		}
		return result;
	}

	/** IVisualizerModelObject version of compareTO() */
	@Override
	public int compareTo(IVisualizerModelObject o) {
		if (o != null) {
			if (o.getClass() == this.getClass()) {
				return compareTo((VisualizerCore)o);
			}
		}
		return 1;
	}
	
}
