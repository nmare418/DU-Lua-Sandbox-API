/*    
Navigator.java 
    Copyright (C) 2021 Stephane Boivin (Discord: Nmare418#6397)
    
    This file is part of "DU lua sandbox API".

    "DU lua sandbox API" is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    "DU lua sandbox API" is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with "DU lua sandbox API".  If not, see <https://www.gnu.org/licenses/>.
*/
package duJavaAPI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import sandbox.DUElement;
import sandbox.execTimer;

public class Navigator extends BaseElement {
public JWebBrowser web = null;
public String html = "";
int sizeX = 200;
int sizeY = 100;   
int timerId = 0;
int isRemoteControlled = 0;

public Navigator(int pid, int px, int py, boolean pverboseJava) {
	name = "Navigator";
	x = px;
	y = py;		
	
	verboseJava = pverboseJava;

	// create panel
	panel = new JPanel();		
	panel.setLayout(null);
	panel.setBorder(LineBorder.createBlackLineBorder());
	panel.setBackground(Color.black);		
	panel.setBounds(x, y, sizeX, sizeY);
	
	// white frame
	JPanel lblPicWhite = new JPanel();		
 	lblPicWhite.setBounds(1, 1, sizeX-3, sizeY-3);
 	lblPicWhite.setBackground(Color.white);
	panel.add(lblPicWhite, 1, 0);

	// picture
 	JLabel lblPic = new JLabel();
 	setPicture(lblPic, "src/pictures/elements/Navigator.png", 9, 5, 55, 71);
	panel.add(lblPic, 1, 0);

	// stats
	CreateStatPanel(name+" ("+id+")", sizeX, sizeY);		
	AddtoStatPanel("Remote: ", Integer.toString(isRemoteControlled));
	AddtoStatPanel("Gravity: ", ".9");
	
	panel.add(stats, 1, 0);
	
	// lua require and interface 
	urlAPI = "src/duElementAPI/navigator.lua";
    luaCall = name+" = createInterfaceNavigator(\""+pid+"\", \""+name+"\")";		
    if(verboseJava) System.out.println("[JAVA] Navigator created");

}

@Override	
public void update(String[] param) {
	String pcommand = param[0];
    String[] cmd1 = {"getType"};
    String[] cmd2 = {"getState"};    
    String[] cmd3 = {"0", " "};
//    System.out.println("[JAVA] Navigator update (" + param[0] + ", " + param[1] + ", " + param[2] + ")"); 
    switch (pcommand) {
		case "setEngineCommand": // to do
			// param[1] taglist 
			// param[2] acceleration (vec3)
			// param[3] angularAcceleration (vec3) 
			// param[4] keepForceCollinearity
			// param[5] keepTorqueCollinearity, 
			// param[6] priority1SubTags
			// param[7] priority2SubTags
			// param[8] priority3SubTags
			// param[9] toleranceRatioToStopCommand
			break;
		case "setEngineThrust": // to do
			// param[1] taglist 
			// param[2] thrust (newton)
		case "setAxisCommandValue": // to do
			// param[1] axis 
			// param[2] commandValue
			break;
		case "setupAxisCommandProperties": // to do
			// param[1] axis 
			// param[2] commandType
			break;
		case "cancelCurrentControlMasterMode": // to do
			break;
		case "setupControlMasterModeProperties": // to do
			// param[1] controlMasterModeId
			// param[2] displayName
			break;
		case "extendLandingGears":
			for (DUElement elem : sandbox.elements) {
			   if(elem == null) continue;
			   if(elem.GetType().equals("duJavaAPI.ToggleUnit")) {
				   if(elem.element.get(cmd1).equals("LandingGear") ) {
					   if((int)elem.element.get(cmd2) == 0) {
						   cmd3[0] = "1";
						   cmd3[1] = elem.element.name;
						   elem.element.update(cmd3);
					   }
				   }
			   }
			}
			break;
		case "retractLandingGears": 
			for (DUElement elem : sandbox.elements) {
			   if(elem == null) continue;
			   if(elem.GetType().equals("duJavaAPI.ToggleUnit")) {
				   if(elem.element.get(cmd1).equals("LandingGear") ) {
					   if((int)elem.element.get(cmd2) == 1) {
						   cmd3[0] = "0";
						   cmd3[1] = elem.element.name;
						   elem.element.update(cmd3);
					   }
				   }
			   }			   
			}
		    break;
		case "switchOnHeadlights": 
			for (DUElement elem : sandbox.elements) {
			   if(elem == null) continue;
			   if(elem.GetType().equals("duJavaAPI.ToggleUnit")) {
				   if(elem.element.get(cmd1).equals("Light") ) {
					   if((int)elem.element.get(cmd2) == 0) {
						   cmd3[0] = "1";
						   cmd3[1] = elem.element.name;
						   elem.element.update(cmd3);
					   }
				   }
			   }			   
			}
		    break;
		case "switchOffHeadlights": 
			for (DUElement elem : sandbox.elements) {
			   if(elem == null) continue;
			   if(elem.GetType().equals("duJavaAPI.ToggleUnit")) {
				   if(elem.element.get(cmd1).equals("Light") ) {
					   if((int)elem.element.get(cmd2) == 1) {
						   cmd3[0] = "0";
						   cmd3[1] = elem.element.name;
						   elem.element.update(cmd3);
					   }
				   }
			   }			   
			}
		    break;
		case "_setRemoteControlled": 
			isRemoteControlled = Integer.valueOf(param[1]);
			break;
    }
}

@Override
public Object get(String[] param) {    	
	String pcommand = param[0];
    switch (pcommand) {
		case "isRemoteControlled": 
			return isRemoteControlled;
		case "isMouseControlActivated": 
			return 1;
		case "getAtmosphereDensity": // to do 
	        return .9;
		case "getClosestPlanetInfluence": // to do
	        return 1;
		case "getAxisCommandValue": // to do
			// param[1] axis 
	        return 1;
		case "getControlMasterModeId": // to do
			return 1;
		case "isAnyLandingGearExtended": 
	        String[] cmd1 = {"getType"};
	        String[] cmd2 = {"getState"};
			for (DUElement elem : sandbox.elements) {
			   if(elem == null) continue;
			   if(elem.GetType().equals("duJavaAPI.ToggleUnit")) {
				   if(elem.element.get(cmd1).equals("LandingGear") ) {
					   if((int)elem.element.get(cmd2) == 1) return 1; 
				   }
			   }			   
			} 
			return 0;
    }    
    return -1;
 }	
}
