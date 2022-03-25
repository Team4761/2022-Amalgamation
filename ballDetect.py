import cv2
import numpy as np
import imutils
import argparse
from collections import deque

ap = argparse.ArgumentParser()
ap.add_argument("-v", "--video",help="path to the (optional) video file")
ap.add_argument("-b", "--buffer", type=int, default=128,help="max buffer size")
args = vars(ap.parse_args())

vid = cv2.VideoCapture(0)

#(99, 61, 97),(243, 247, 190)
#(48,37,29),(216,175,141)
iteration = int(4)
isMasked = int(0)
colorLower, colorUpper = (12, 69, 191),(77, 177, 255)
pts = deque(maxlen=args["buffer"])

def RL(value):
    global colorLower
    colorLower = (value,colorLower[1],colorLower[2])
def GL(value):
    global colorLower
    colorLower = (colorLower[0],value,colorLower[2])
def BL(value):
    global colorLower
    colorLower = (colorLower[0],colorLower[1],value)
def RU(value):
    global colorUpper
    colorUpper = (value,colorUpper[1],colorUpper[2])
def GU(value):
    global colorUpper
    colorUpper = (colorUpper[0],value,colorUpper[2])
def BU(value):
    global colorUpper
    colorUpper = (colorUpper[0],colorUpper[1],value)
def decIterate(value):
    global iteration
    iteration = int(value)
def setMasked(value):
    global isMasked
    isMasked = int(value)
def printColors(value):
    print(colorLower,",",colorUpper)

def debug():
    cv2.namedWindow("Slides")
    cv2.createTrackbar('R_lower', "Slides", colorLower[0], 255, RL)
    cv2.createTrackbar('G_lower', "Slides", colorLower[1], 255, GL)
    cv2.createTrackbar('B_lower', "Slides", colorLower[2], 255, BL)
    cv2.createTrackbar('R_upper', "Slides", colorUpper[0], 255, RU)
    cv2.createTrackbar('G_upper', "Slides", colorUpper[1], 255, GU)
    cv2.createTrackbar('B_upper', "Slides", colorUpper[2], 255, BU)
    cv2.createTrackbar('Iteration', "Slides", iteration, 100, decIterate)
    cv2.createTrackbar('Masked?',"Slides", 0, 1, setMasked)
    cv2.createTrackbar('Print RGB',"Slides", 0, 1, printColors)

debug()
while True:
    ret,frame = vid.read()
    frame = cv2.flip(frame,1)
    #mask = cv2.inRange(frame, lower, upper)

    fBlurred = cv2.GaussianBlur(frame, (11, 11), 0)
    fHSV = cv2.cvtColor(fBlurred, cv2.COLOR_BGR2HSV)

    mask = cv2.inRange(fHSV, colorLower, colorUpper)
    mask = cv2.erode(mask, None, iterations=iteration)
    mask = cv2.dilate(mask, None, iterations=iteration)

    cnts = cv2.findContours(mask.copy(), cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_SIMPLE)
    cnts = imutils.grab_contours(cnts)

    if len(cnts) > 0:
        c = max(cnts, key=cv2.contourArea)
        (x,y), radius = cv2.minEnclosingCircle(c)
        M = cv2.moments(c)
        center = (int(M["m10"] / M["m00"]), int(M["m01"] / M["m00"]))
        
        if radius > 20:
            cv2.circle(frame, (int(x), int(y)), int(radius),(0, 255, 255), 2)
            cv2.circle(frame, center, 5, (0, 0, 255), -1)
            pts.appendleft(center)

    for i in range(1, len(pts)):
        if pts[i - 1] is None or pts[i] is None:
            continue
        thickness = int(np.sqrt(args["buffer"] / float(i + 1)) * 2.5)
        cv2.line(frame, pts[i - 1], pts[i], (0, 0, 255), thickness)

    
    if isMasked == 0:
        cv2.imshow("Frame",frame)
    else:
        cv2.imshow("Frame",mask)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
vid.release()
cv2.destroyAllWindows()