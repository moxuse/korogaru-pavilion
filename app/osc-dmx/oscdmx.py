#!/usr/bin/env python

import serial, sys, time
from OSC import OSCServer
from time import sleep
import numpy as np

START_VAL = 0x7E
END_VAL = 0xE7

#COM_BAUD = 57600
COM_BAUD = 250000
COM_TIMEOUT = 0.005
USB_PORT_NAME = "/dev/tty.usbserial-EN119503"

PORT_NUM = 5000

LABELS = {     
           'GET_WIDGET_PARAMETERS' :3,  #unused
           'SET_WIDGET_PARAMETERS' :4,  #unused
           'RX_DMX_PACKET'         :5,  #unused
           'TX_DMX_PACKET'         :6,
           'TX_RDM_PACKET_REQUEST' :7,  #unused
           'RX_DMX_ON_CHANGE'      :8,  #unused
          }

server = OSCServer( ("localhost", PORT_NUM) )
server.timeout = 0
run = True
dmx_frame = list()

def handle_timeout(self):
  self.timed_out = True

import types
server.handle_timeout = types.MethodType(handle_timeout, server)

def user_callback(path, tags, args, source):
  i = 0
  print ("/dmx data: " ,path,tags,ord(args[0][0]),source) 
  for item in args[0]:
    print(i, ord(item))
    dmx_frame[i] = ord(item)
    i += 1
  writeDMX()

  

def quit_callback(path, tags, args, source):

  global run
  run = False

server.addMsgHandler( "/dmx", user_callback )


def each_frame():

  server.timed_out = False

  while not server.timed_out:
      server.handle_request()

def setupDMX():
  global com
  for i in xrange (512):
    dmx_frame.append(0)
  try:
    com = serial.Serial(USB_PORT_NAME, baudrate=COM_BAUD, timeout=COM_TIMEOUT)
    print(com)
  except:
    print "Could not open COM%s, quitting application" % (USB_PORT_NAME)
    sys.exit(0)

def writeDMX():
  packet = []
  packet.append(chr(START_VAL))
  packet.append(chr(LABELS['TX_DMX_PACKET']))
  packet.append(chr(len(dmx_frame) & 0xFF))
  packet.append(chr((len(dmx_frame) >> 8) & 0xFF))
  
  for j in xrange(len(dmx_frame)):
      packet.append(chr(dmx_frame[j]))
      
  packet.append(chr(END_VAL))
  print(packet)
  com.write(''.join(packet)) 

setupDMX()

while run:

  sleep(0.005)

  each_frame()

server.close()
