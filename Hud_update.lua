--[[
    Hud_update.lua
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
]]

t = earthDate(system.getTime())['hour']..':'..earthDate(system.getTime())['min']..":"..earthDate(system.getTime())['sec']

system.setScreen(string.format(htmlBox, t, core.getConstructMass(), core.g(),
										core.getAltitude(), (earthDate(system.getTime())['sec']*375)/60 ));
