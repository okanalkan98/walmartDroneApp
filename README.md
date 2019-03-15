Goal: Find the best scheduling approach that maximizes the net promoter score (NPS) amongst drone-delivery customers.

Assumptions: 
---------------------given assumptions in the assignment------------------------
1- The town owns one drone, which is allowed to operate from 6 a.m. until 10 p.m. 
2- The town is organized in a perfect grid, with a warehouse and drone-launch facility at the center. 
3- All deliveries originate at the warehouse and are carried by a drone to a customer location.
4- A drone's "ground speed" is exactly one horizontal or vertical grid block per minute.
-------------------------------added assumptions--------------------------------
5- Drone can be loaded one order at a time.
6- No load or unload time.
7- Drone flies on the shortest route (hypotenuse) between warehouse and customer location.
8- Drone can deliver up to 12 hours distance.
9- All orders are supposed to be ready for shipment before 6 a.m.
11- Drone does not pick up the order if it will back to the Warehouse after 10 p.m.
12- Same set of data will be used for all scheduling approaches to achieve equal comparison. 
13- The comparison will be implemented for below four scheduling approaches; 
	a. FIFO (First in first out) 
	b. LIFO (Last in first out)
	c. SDTFO (Shortest delivery time first out) 
	d. LDTFO (Longest delivery time first out)

Test Case:
1. Load input file.
2. Calculate delivery time for each order.
3. Sort the orders according to each scheduling approach separately.
4. Calculate departure time for each order.
5. Calculate NPS values for each scheduling approach.
6. Create output files for each scheduling approach separately.
7. Sort NPS values ascending.
8. Print sorted NPS value list to see the best result.
