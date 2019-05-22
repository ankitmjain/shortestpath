package com.dhs.utility;

class Edge{
	
    public final Vertex target;
    public final double weight;
    public final double tdelay;
    public Edge(Vertex argTarget, double argWeight,double delay)
    { target = argTarget; weight = argWeight;tdelay = delay;}

}