/*    
    MAIN.java 
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



package sandbox;

import java.awt.EventQueue;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class MAIN {
    public static execWindow execWin;
    public static float version = (float)0.700;
	private static PreLoad pre;
	public static String preloadFile = "";
	public static String executable = "duSandbox.exe";
	public static winConsole wConsole;
	
	public static void main(String[] args) {     
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// setup a system Console
					wConsole = new winConsole("", System.out);
					System.setOut(new PrintStream (wConsole));
					
					if(args.length == 0) { 
						preloadFile = "default_load.lua";
						System.out.println("missing args[0]: \"default_load.lua\" will be used as preload file.");		 						 
					} else {
						preloadFile = args[0];
					} 

				    // start preload engine.
				    pre = new PreLoad(preloadFile);
					
				} catch (Exception e) { e.printStackTrace(); }
			}
		});

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		NativeInterface.open();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					execWin = new execWindow(pre, wConsole, preloadFile);
					pre = null;
				} catch (Exception e) { e.printStackTrace(); }
			}
		});

    }
}