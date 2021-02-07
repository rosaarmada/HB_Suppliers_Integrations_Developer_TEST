# HB_Suppliers_Integrations_Developer_TEST
Hotelbeds Suppliers Integrations Developer TEST
1. Write a Java program implementing the HackerDetector interface (outlined below) which
defines a single method 'parseLine'. The method should take one line of the log file at a time
and return the IP address if any suspicious activity is identified or null if the activity appears to
be normal.
package com.hotelbeds.supplierintegrations.hackertest.detector
public interface HackerDetector {
String parseLine(String line);
}
2. Write a function that returns the number of minutes (rounded down) between two
timestamps time1 and time2 in RFC 2822 format (ie: Thu, 21 Dec 2000 16:01:07 +0200).
