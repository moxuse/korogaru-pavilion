#!/usr/bin/env python

import serial, sys, time
from OSC import OSCServer
from time import sleep
import numpy as np
import optparse
from setproctitle import setproctitle

START_VAL = 0x7E
END_VAL = 0xE7

COM_BAUD = 57600
#COM_BAUD = 250000
COM_TIMEOUT = 0.005
USB_PORT_NAME = "/dev/tty.usbserial-EN119503"
HOST_NAME = "localhost"
PORT_NUM = 5000

TX_DMX_PACKET = 6

server = OSCServer( (HOST_NAME, PORT_NUM) )
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
    #print(i, ord(item))
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
    print "Could not open COM%s, quitting application.. \n" % (USB_PORT_NAME)

    sys.exit(1)

def writeDMX():
  packet = []
  packet.append(chr(START_VAL))
  packet.append(chr(TX_DMX_PACKET))
  packet.append(chr(len(dmx_frame)+1 & 0xFF))
  packet.append(chr((len(dmx_frame)+1 >> 8) & 0xFF))
  packet.append(chr(0))
  for j in xrange(len(dmx_frame)):
      packet.append(chr(dmx_frame[j]))
      
  packet.append(chr(END_VAL))
  print(packet)
  com.write(''.join(packet)) 


def main():
  setproctitle("osc-dmx")

  parser = optparse.OptionParser('Usage: %prog [options]')
  parser.add_option('-d', '--device',
      action='store', dest='USB_PORT_NAME', type='string',
      help='DMX Device ID NAME')
  parser.add_option('-H', '--HOST',
      action='store', dest='HOST_NAME', type='string',
      help='Osc server host address')
  parser.add_option('-p', '--port',
      action='store', dest='PORT_NUM', type='int',
      help='Osc server port number')
  parser.add_option('-b', '--baudrate',
      action='store', dest='COM_BAUD', type='int',
      help='Serial Port Baud Rate')

  (options, args) = parser.parse_args()
  if len(args) > 4:
    parser.error('too many arguments')

  COM_BAUD = options.COM_BAUD
  USB_PORT_NAME = options.USB_PORT_NAME
  HOST_NAME = options.HOST_NAME
  PORT_NUM = options.PORT_NUM

  setupDMX()

  while run:

    sleep(0.005)

    each_frame()

  server.close()

if __name__ == '__main__':
  main()
