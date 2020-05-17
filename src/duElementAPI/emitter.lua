--[[    
    emitter.lua 
    Copyright (C) 2020 Stephane Boivin (Devgeek studio enr.)
    
    This file is part of "DU offline sandbox API".

    "DU offline sandbox API" is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    "DU offline sandbox API" is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with "DU offline sandbox API".  If not, see <https://www.gnu.org/licenses/>.
]]
Iemitter = {id, name}

function Iemitter:new(o)
  o = o or {}
  setmetatable(o, self)
  self.__index = self

  o.maxHitPoints = 2000
  o.hitPoints = 2000
  o.mass = 47.21075
  o.class = 'EmitterUnit'
  o.integrity = 100

  o.emitter = Emitter:new{id = o.id, name = o.name}
  o.generic = genericElement:new{id = o.id, maxHitPoints = o.maxHitPoints, hitPoints = o.hitPoints, mass = o.mass, class = o.class, integrity = o.integrity }

  return o
end

function createInterfaceEmitter(id, name)

  local obj = Iemitter:new{id=id,name=name}
  obj.getRange = function() return obj.emitter:getRange() end
  obj.send = function(channel, message) obj.emitter:send(channel, message) end

  obj.hide = function() obj.generic:hide() end
  obj.show = function() obj.generic:show() end
  obj.getData = function() obj.generic:getData() end
  obj.getId = function() return obj.generic:getId() end
  obj.getMass = function() return obj.generic:getMass() end
  obj.getElementClass = function() return obj.generic:getElementClass() end
  obj.getIntegrity = function() return obj.generic:getIntegrity() end
  obj.getHitPoints = function() return obj.generic:getHitPoints() end
  obj.getMaxHitPoints = function() return obj.generic:getMaxHitPoints() end
  obj._setMass = function(m) obj.generic:setMass(m) end
  obj._setMaxHitPoints = function(mhp) obj.generic:setMaxHitPoints(mhp) end
  obj._setHitPoints = function(hp) obj.generic:setHitPoints(hp) end
  obj._setIntegrity = function(int) obj.generic:setIntegrity(int) end
   
  return obj
end

-- State 0 = closed
-- State 1 = open
Emitter = {id, name = "Emitter", range = 100}

function Emitter:new(o)
  o = o or {}
  setmetatable(o, self)
  self.__index = self

  range = 10
  
  if _verboseLUA == 1 then print("[LUA] Emitter ["..o.name.."] created with the id ["..o.id.."]") end

  return o
end

function Emitter:send(channel, message)
  JavaWindow:setState(self.id, {"send", channel, message})         
end

function Emitter:getRange()
  return self.range
end

