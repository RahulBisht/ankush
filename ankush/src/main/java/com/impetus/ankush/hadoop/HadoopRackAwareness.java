/*******************************************************************************
 * ===========================================================
 * Ankush : Big Data Cluster Management Solution
 * ===========================================================
 * 
 * (C) Copyright 2014, by Impetus Technologies
 * 
 * This is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License (LGPL v3) as
 * published by the Free Software Foundation;
 * 
 * This software is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this software; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 ******************************************************************************/
package com.impetus.ankush.hadoop;

import java.util.Set;
import com.impetus.ankush.common.framework.config.NodeConf;


/**
 * @author Akhil
 *
 */
public class HadoopRackAwareness {
	
	/** The Constant LINE_SEPERATOR. */
	private static final String LINE_SEPERATOR = System
			.getProperty("line.separator");
	
	/**
	 * Creates the file content for topology data.
	 *
	 * @param nodeConfs the node confs
	 * @return the string
	 */
	public static String createRackFileContents(Set<NodeConf> nodeConfs) {
		String topologyFileContent = "";
		for(NodeConf objNodeConf : nodeConfs) {
			topologyFileContent += objNodeConf.getPublicIp()  + " " 
									+ "/" +  objNodeConf.getDatacenter()
									+ "/" + objNodeConf.getRack();
			topologyFileContent += HadoopRackAwareness.LINE_SEPERATOR;
		}
		return topologyFileContent;
	}
}
