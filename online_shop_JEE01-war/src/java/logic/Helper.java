/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mick_02
 */
public class Helper {

	public static enum Part {
		CONTROLLER, ACTION, PARAMETER, ERROR
	}
//	private final static String PATH = "de.autoverwaltung";
	private final static String CONTROLLER_PATH = "controller";
	private static String controllerName;

	private Helper() {
	}
	
	public static String getControllerName() {
		return controllerName;
	}

	private static String[] cut(String path) {
		String[] parts = null;

		if (path != null) {
			parts = path.substring(1).split("/");
		}
		return parts;
	}

	public static String getFromRequest(HttpServletRequest request, Part partName) {
		Part part = partName;
		String[] pathParts = cut(request.getPathInfo().toLowerCase());
		StringBuilder output = new StringBuilder();

		switch (part) {
		
		case CONTROLLER:
			output.append(CONTROLLER_PATH);
			output.append(".");
			try {
				controllerName =  Character.valueOf(pathParts[0].charAt(0)).toString().toUpperCase() + pathParts[0].substring(1);
				output.append(controllerName);
			} catch (Exception e) {
				output.append("Index");
			}
			output.append("Controller");
			break;
			
		case ACTION:
			try {
				output.append(pathParts[1]);
			} catch (Exception e) {
				output.append("index");
			}
			output.append("Action");
			break;
			
		case PARAMETER:
			try {
				output.append(pathParts[2]);
			} catch (Exception e) {}
			break;
			
		case ERROR:
			break;
		}
		return output.toString();
	}
}
