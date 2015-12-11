package com.mygdx.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity{
Entity target;
Node player;
ArrayList<Node> openlist;
ArrayList<Node> closedlist;
int g,h;
Node parent;
int[][] map;
Node node;
public	ArrayList<Node> pathList;
SpriteBatch batch = new SpriteBatch();
	public Enemy(MyGdxGame game, float x, float y, int width, int height, float speed, Texture texture, Entity player, int[][] map) {
		super(game, x, y, width, height, speed, texture);
		// TODO Auto-generated constructor stub
		openlist = new ArrayList<Node> ();
	closedlist=new	ArrayList<Node>();
		this.target = player;
		this.player = new Node((int)player.x/40,(int)player.y/40,0,0);//just a temporary holder for the player
		parent = new Node((int)(x/40),(int)y/40,0,0);//we now set the opening parent
		g=0;
		h=0;
		pathList = new	ArrayList<Node>();
		this.map = map;
		openlist.add(parent);//add the starting point
	}
	public void addToOpenList(Node node){//checks if its int the closed list or the open list
		for(Node n : closedlist){
			if(node.same(n)){
				return ;//do not add something it the closed lists
			}	
		}
		for(int i = 0;i<openlist.size();i++){
			if(node.same(openlist.get(i))){//if found, check which one has the most
				openlist.get(i).replaceWith(node);
				return ;
			}
		}//its in non of the list
		openlist.add(node);
		
		
	}
	public void sortList(){
	
	Collections.sort(openlist,Node.NodeComparator);//the list is sorted!
	}
	public boolean explode(Node pop,Entity target){
		//ArrayList<Node> others = new ArrayList<Node>();
		player = new Node((int)target.x/40,(int)target.y/40,0,0,null);
		if(pop.x>0){
		if((pop.x>0& pop.y>=0 & map[pop.x-1][Math.abs(16-pop.y)]==0 )){//the left end
			int h =(int)Math.abs(pop.x-1- (target.x/40))+(int)Math.abs(pop.y-target.y/40);
		
			Node n =new Node(pop.x-1,pop.y,h,pop.g+10,pop);
			addToOpenList(n);//check if the node can be added to the ope list
			if(n.same(player)){//we found the player, exit
				node=n; 
				//n.renderNodes();
			return true;	
			}
			//add all the left nodes
		//	others.add(new Node(this.x-1,this.y, true,'L'));
	}
		if( pop.x>0& pop.y<15 &map[pop.x-1][Math.abs(16-pop.y+1)]==0 ){
			int h =(int)Math.abs(pop.x-1- target.x/40)+(int)Math.abs(pop.y+1-(target.y/40));
			
			Node n =new Node(pop.x-1,pop.y+1,h,pop.g+14,pop);
			addToOpenList(n);
			if(n.same(player)){//we found the player, exit
			//	n.renderNodes();
				node=n;
				return true;	
				}
		}
		if( pop.x>0& pop.y>0&map[pop.x-1][Math.abs(16-pop.y-1)]==0 ){
			int h =(int)Math.abs(pop.x-1- target.x/40)+(int)Math.abs(pop.y-1-(target.y/40));
			
			Node n =new Node(pop.x-1,pop.y-1,h,pop.g+14,pop);
			addToOpenList(n);
			if(n.same(player)){//we found the player, exit
				//n.renderNodes();
				node=n;
				return true;	
				}
		}}
		//on the right side
		if((pop.x<29& pop.y>=0 & map[pop.x+1][Math.abs(16-pop.y)]==0 )){//the left end
			int h =(int)Math.abs(pop.x+1- (target.x/40))+(int)Math.abs(pop.y-target.y/40);
		
			Node n =new Node(pop.x+1,pop.y,h,pop.g+10,pop);
			addToOpenList(n);//check if the node can be added to the ope list
			//add all the left nodes
			if(n.same(player)){//we found the player, exit
				//n.renderNodes();
				node=n;
				return true;	
				}
	}
		if( pop.x<29& pop.y<15&map[pop.x+1][Math.abs(16-pop.y+1)]==0 ){
			int h =(int)Math.abs(pop.x+1- target.x/40)+(int)Math.abs(pop.y+1-(target.y/40));
		
			Node n =new Node(pop.x+1,pop.y+1,h,pop.g+14,pop);
			addToOpenList(n);
			if(n.same(player)){//we found the player, exit
			//	n.renderNodes();
				node=n;
				return true;	
				}
		}
		if( pop.x<29& pop.y>0&map[pop.x+1][Math.abs(16-pop.y-1)]==0 ){
			int h =(int)Math.abs(pop.x+1- target.x/40)+(int)Math.abs(pop.y-1-(target.y/40));
			Node n =new Node(pop.x+1,pop.y-1,h,pop.g+14,pop);
			addToOpenList(n);
			if(n.same(player)){//we found the player, exit
				//n.renderNodes();
				node=n;
				return true;	
				}
		}
		//the middle top and the bottom
		if( pop.x>0& pop.y<15&map[pop.x][Math.abs(16-pop.y+1)]==0 ){//move up
			int h =(int)Math.abs(pop.x- target.x/40)+(int)Math.abs(pop.y+1-(target.y/40));
			Node n =new Node(pop.x,pop.y+1,h,pop.g+10,pop);
			addToOpenList(n);
			if(n.same(player)){//we found the player, exit
				//n.renderNodes();
				node=n;
				return true;	
				}
		}
		if( pop.x>=0& pop.y>0&map[pop.x][Math.abs(16-pop.y-1)]==0 ){//move up
			int h =(int)Math.abs(pop.x- target.x/40)+(int)Math.abs(pop.y-1-(target.y/40));//the sum of the x and y distance
			Node n =new Node(pop.x,pop.y-1,h,pop.g+10,pop);//f is h(distance to target) g is distance to target
			addToOpenList(n);//add to array then check if we are not at the end
			if(n.same(player)){//we found the player, exit
				//n.renderNodes();
				node=n;
				return true;	
				}//the one at the end of the list is the one to find the player
		}
		//done popping!!!
		sortList();//after adding to the list we now sort it
		return false;
	}
	public void pathFinding(Entity target){
		while(openlist.size()>0){
			//repetitively do find the next way out
			closedlist.add( openlist.get(0));// the one we pop is now added to hte closed list
			//the list is sorted so this is the last value
		Node q=	openlist.remove(0);
		//generate successors
	if	(explode(q,target)==true) {
	//	n.renderNodes();//tehy will all be added to th open list
	pathList=	converToList(openlist.get(openlist.size()-1));
	Node next =pathList.get(pathList.size()-2);//the nextt position after the last value
	node = next;
	this.parent=new Node(next.x,next.y,0,0);//re-initialise
	this.x=next.x*40;this.y= next.y*40;//move sprite
	openlist = new ArrayList<Node> ();
	closedlist=new	ArrayList<Node>();
	openlist.add(parent);
	g=0;h=0;
	System.out.println("Path recalculated------------------------------------------------");
	return;}
		}//recalculate at every step
		}
	//@Override
public void renderPath(){
	//node = node.nextPosit
	if(pathList==null&&pathList.size()==0)return;
	for (Node n : pathList){
		n.renderNodes();//print all the nodes in the list
	}
}
//Spritebatch batch = new SprsiteBatch();
public void followPath(){
//	follow n
	if(pathList==null&&pathList.size()==0)return;
	batch.begin();
	node=pathList.remove(pathList.size()-1);
	batch.draw(new Texture("hunter.png"), node.x*40, node.y*40, 40,40);
	this.x = node.x*40;
	this.y = node.y*40;
	//if(node)
	//remove the last one and make it node
	batch.end();
	//node = node.nextPosition();
	
}
public ArrayList<Node> converToList(Node node){//you have the leading node, now get the path, remeber to read in reverse
	ArrayList<Node> pathList = new ArrayList<Node>();
	while(node!=null){
		pathList.add(node);
		node = node.parent;
	}
	return pathList;
}
}