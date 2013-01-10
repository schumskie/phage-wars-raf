import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import engine.Sprite;
import engine.SpritePanel;


public class EditPanel extends SpritePanel implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1983250486298033038L;
	ArrayList<Planet> planets = new ArrayList<Planet>();
	Planet selectedPlanet = null;
	Planet editPlanet = null;
	public EditPanel(){
		setBackground(Color.white);
		setPreferredSize(new Dimension(640, 480));
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Sprite background = new Sprite();
		background.setImg(loadImage("/resources/background.png").getImage());
		sprites.add(background);
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		/*
		for (int i = 0; i < planets.size(); i++)
			planets.get(i).paint(g2);*/
		
		if (editPlanet != null)
			editPlanet.paint(g2);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Iterator<Planet> it = planets.iterator();
		while (it.hasNext()) {
			Planet p = it.next();
			int dx = (int)(p.getX() - e.getX());
			int dy = (int)(p.getY() - e.getY());
			if (Math.sqrt(dx*dx+dy*dy)< p.getRadius()) {
				selectedPlanet = p;
				break;
			}
		}
		if (selectedPlanet == null && e.getButton() == MouseEvent.BUTTON1){
			editPlanet = new Planet();
			editPlanet.setX(e.getX());
			editPlanet.setY(e.getY());
		}
		else {
			if (e.getButton() == MouseEvent.BUTTON2 && selectedPlanet != null) {
				Planet copyPlanet = new Planet();
				copyPlanet.setRadius(selectedPlanet.getRadius());
				copyPlanet.setNumber(selectedPlanet.getNumber());
				copyPlanet.setX(selectedPlanet.getX());
				copyPlanet.setY(selectedPlanet.getY());
				
				planets.add(copyPlanet);
				sprites.add(copyPlanet);
				//planets.add(copyPlanet);
				
				selectedPlanet = copyPlanet;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedPlanet = null;
		if (e.getButton() == MouseEvent.BUTTON1){
			if (editPlanet != null) {
				if (editPlanet.getRadius() < 15)
					editPlanet.setRadius(15);
				planets.add(editPlanet);
				sprites.add(editPlanet);
				editPlanet = null;
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			Iterator<Planet> it = planets.iterator();
			Planet p = null;
			while (it.hasNext()) {
				p = it.next();
				int dx = (int)(p.getX() - e.getX());
				int dy = (int)(p.getY() - e.getY());
				if (Math.sqrt(dx*dx+dy*dy)< p.getRadius()) {
					sprites.remove(p);
					planets.remove(p);
					break;
				}
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if (editPlanet != null) {
			int dx = (int)(editPlanet.getX() - e.getX());
			int dy = (int)(editPlanet.getY() - e.getY());
			editPlanet.setRadius((int)Math.sqrt(dx*dx+dy*dy));
		}
		if (selectedPlanet != null) {
			selectedPlanet.setX(e.getX());
			selectedPlanet.setY(e.getY());
		}

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
