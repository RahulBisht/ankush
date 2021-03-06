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
package com.impetus.ankush.common.controller.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.impetus.ankush.AppStoreWrapper;
import com.impetus.ankush.common.alerts.AlertsConf;
import com.impetus.ankush.common.alerts.EventManager;
import com.impetus.ankush.common.constant.Constant;
import com.impetus.ankush.common.domain.Cluster;
import com.impetus.ankush.common.domain.User;
import com.impetus.ankush.common.framework.ClusterMonitor;
import com.impetus.ankush.common.service.ConfigurationManager;
import com.impetus.ankush.common.service.GenericManager;
import com.impetus.ankush.common.service.MonitoringManager;
import com.impetus.ankush.common.utils.AnkushLogger;
import com.impetus.ankush.common.utils.ResponseWrapper;

/**
 * The Class MonitorController.
 * 
 * @author nikunj
 */

@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

	/** The logger. */
	private static AnkushLogger logger = new AnkushLogger(
			MonitorController.class);

	/**
	 * Monitor.
	 * 
	 * @param clusterId
	 *            the cluster id
	 * @param action
	 *            the action
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{clusterId}/{action}")
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Map>> monitor(
			@PathVariable("clusterId") Long clusterId,
			@PathVariable("action") String action, HttpServletRequest request) {
		Map map = new ClusterMonitor().getMap(clusterId, action,
				request.getParameterMap());
		return wrapResponse(map, HttpStatus.OK, HttpStatus.OK.toString(),
				"Cluster details.");
	}

	/**
	 * Monitor.
	 * 
	 * @param clusterId
	 *            the cluster id
	 * @param parameterMap
	 *            the parameter map
	 * @param action
	 *            the action
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/{clusterId}/{action}")
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Map>> monitor(
			@PathVariable("clusterId") Long clusterId,
			@RequestBody Map parameterMap,
			@PathVariable("action") String action, HttpServletRequest request) {
		String loggedUser = ((User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		parameterMap.put("loggedUser", loggedUser);
		if(request.getParameterMap().containsKey(Constant.Keys.TECHNOLOGY)){
			parameterMap.put(Constant.Keys.TECHNOLOGY,request.getParameter(Constant.Keys.TECHNOLOGY));
		}
		Map map = new ClusterMonitor().getMapPost(clusterId, action,
				parameterMap);
		return wrapResponse(map, HttpStatus.OK, HttpStatus.OK.toString(),
				"Cluster detail");
	}

	/**
	 * Gets the configuration.
	 * 
	 * @param id
	 *            the id
	 * @return the configuration
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/audits")
	@ResponseBody
	public ResponseEntity<ResponseWrapper<List>> getConfiguration(
			@PathVariable Long id) throws Exception {

		try {
			ConfigurationManager manager = new ConfigurationManager();
			return wrapResponse(manager.getConfiguration(id), HttpStatus.OK,
					HttpStatus.OK.toString(), "audit logs");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return wrapResponse(null, HttpStatus.OK, HttpStatus.OK.toString(),
					"audit logs");
		}
	}

	/**
	 * Gets the monitoring info.
	 * 
	 * @param nodeId
	 *            the node id
	 * @param action
	 *            the action
	 * @return the monitoring info
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/node/{nodeId}/{action}")
	@ResponseBody
	public ResponseEntity<ResponseWrapper<Object>> getMonitoringInfo(
			@PathVariable("nodeId") Long nodeId,
			@PathVariable("action") String action) {

		MonitoringManager manager = new MonitoringManager();
		return wrapResponse(manager.monitor(nodeId, action), HttpStatus.OK,
				HttpStatus.OK.toString(), "system overview details.");
	}

	/**
	 * Sets the alerts conf.
	 * 
	 * @param clusterId
	 *            the cluster id
	 * @param alertsConf
	 *            the alerts conf
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/{clusterId}/alertsConf")
	@ResponseBody
	public ResponseEntity<ResponseWrapper<AlertsConf>> setAlertsConf(
			@PathVariable("clusterId") Long clusterId,
			@RequestBody AlertsConf alertsConf) {

		GenericManager<Cluster, Long> clusterManager = AppStoreWrapper
				.getManager(Constant.Manager.CLUSTER, Cluster.class);

		String description = "Saved alerts configuration.";
		try {
			Cluster cluster = clusterManager.get(clusterId);
			cluster.setAlertConf(alertsConf);
			cluster = clusterManager.save(cluster);
			new EventManager().updateEventSubTypes(cluster);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			description = "Failed to save alerts configuration.";
		}
		return wrapResponse(alertsConf, HttpStatus.OK,
				HttpStatus.OK.toString(), description);
	}

	/**
	 * Gets the alerts conf.
	 * 
	 * @param clusterId
	 *            the cluster id
	 * @return the alerts conf
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{clusterId}/alertsConf")
	@ResponseBody
	public ResponseEntity<ResponseWrapper<AlertsConf>> getAlertsConf(
			@PathVariable("clusterId") Long clusterId) {

		GenericManager<Cluster, Long> clusterManager = AppStoreWrapper
				.getManager(Constant.Manager.CLUSTER, Cluster.class);

		String description = "Alerts configuration.";
		AlertsConf alertsConf = null;
		try {
			Cluster cluster = clusterManager.get(clusterId);
			alertsConf = cluster.getAlertsConf();
		} catch (Exception e) {
			logger.debug(e.getMessage());
			description = "Failed to get alerts configuration.";
		}
		return wrapResponse(alertsConf, HttpStatus.OK,
				HttpStatus.OK.toString(), description);
	}
}
