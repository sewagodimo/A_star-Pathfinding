package com.mygdx.game;

import java.util.ArrayList;
import java.util.Comparator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
//this is the advanced version of the AI*, I improved the A* algorithms

//So that now diagonals having  a longer
public class Node {
//to store each postion as a node value
public	int y,x,h,g,f;//y and x values on the map
public	boolean closed;
//public	char Direction = 'L';
Node parent;
ShapeRenderer shape;
	public Node(int x, int y,int h, int g,Node Parent){
		this.x=x;
		this.y=y;//these are actual values on the map
		//this.h=(int)Math.abs((x-target.x)/40)+(int)Math.abs((y-target.y)/40);//nodes to target
	//	this.g= g;//cost from initial position
		this.closed=false;//
	//	this.Direction= direction;
	//	this.f = g+h;
		this.g = g;
		this.h = h;
		f= h+g;
		this.parent = Parent;
		shape = new ShapeRenderer();
		//this.target = target;
	}
	public Node(int x, int y,int h, int g){
		this.x=x;
		this.y=y;
		this.g = g;
		this.h = h;
		f= h+g;
		//this.h=(int)Math.abs((x-target.x)/40)+(int)Math.abs((y-target.y)/40);//nodes to target
	//	this.g= g;//cost from initial position
		this.closed=false;//
	//	this.Direction= direction;
	//	this.f = g+h;\
		parent = null;
		//this.target = target;
	}
	public boolean same(Node other){
		//does not work well
		Rectangle rect1=	new Rectangle(this.x*40, this.y*40, 40, 40);
		Rectangle rect2=	new Rectangle(other.x*40,other.y*40,40,40);//draw rects around the area
		Rectangle intersect= new Rectangle();
		if(Intersector.intersectRectangles(rect1, rect2, intersect)==true){
			System.out.println("Collosion with player");
			return true;
		}
		if(other.x != this.x) return false;
		if(other.y != this.y) return false;
		return true;
	}
	public void replaceWith(Node other){
		if(this.f>other.f){//our current one is cheaper
			other.h = this.h;//they both have the same x and y so now whic one has a higher h
			other.f = this.f;
			other.h = this.h;
		}
	}
	public void renderNodes(){
		Node n = this;
		shape = new ShapeRenderer();
	//when the parent it null you are back to square zero
			
		//	Rectasngle rect = new Rectangle(this.x,this.y,40,40);
				shape.begin(ShapeType.Line);
				shape.setColor(Color.RED);
				shape.rect(this.x*40, this.y*40,40,40);
				shape.end();
				//System.out.println(n.f);
				//n = n.parent;//draw from the parent

		
	}
	public Node nextPosition(){
		Node n = this;
		while(n!=null&n.parent!=null&n.parent.parent!=null){//return the one with a null grand parent
			n = n.parent;
		}
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.GREEN);
	//	shape.rect(n.x*40, n.y*40,40,40);
		shape.end();
		n = n.parent;//draw from the parent
		return n;
	}
	 final static Comparator<Node> NodeComparator = new Comparator<Node>() {
			public int compare(Node n1, Node n2){
				int f1 = n1.f;
				int f2 = n2.f;
				return f1-f2;//sorted in ascending orde
			//}
		}
		};

}
