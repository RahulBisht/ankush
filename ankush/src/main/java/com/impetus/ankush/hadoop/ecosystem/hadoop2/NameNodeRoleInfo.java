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
/**
 * 
 */
package com.impetus.ankush.hadoop.ecosystem.hadoop2;

/**
 * @author Akhil
 *
 */
public class NameNodeRoleInfo {

	private String nodeIp;
	
	private String nameNodeRole;

	/**
	 * @return the nodeIp
	 */
	public String getNodeIp() {
		return nodeIp;
	}

	/**
	 * @param nodeIp the nodeIp to set
	 */
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}

	/**
	 * @return the nameNodeRole
	 */
	public String getNameNodeRole() {
		return nameNodeRole;
	}

	/**
	 * @param nameNodeRole the nameNodeRole to set
	 */
	public void setNameNodeRole(String nameNodeRole) {
		this.nameNodeRole = nameNodeRole;
	}
}
