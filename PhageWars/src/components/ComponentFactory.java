package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * @author Stefan Jeremic
 *
 */
public class ComponentFactory {
	/**
	 * @param title
	 * @return JButton with title text.
	 */
	public static JButton createButton(String title) {
		JButton button = new GameButton(title);
		return button;
	}
	/**
	 * @param title
	 * @return Jpanel with title text.
	 */
	public static JPanel createPanel(String title){
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 1, 0, 0.5f));
		Border border = BorderFactory.createLineBorder(Color.green);
		TitledBorder titleBorder = BorderFactory.createTitledBorder(border, title);
		titleBorder.setTitleJustification(TitledBorder.CENTER);
		titleBorder.setTitleColor(Color.green);
		panel.setBorder(titleBorder);
		return panel;
	}
	/**
	 * @param title
	 * @return JradioButtion with title text.
	 */
	public static JRadioButton createRadioButton(String title){
		JRadioButton button = new JRadioButton(title){

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				//super.paintComponent(g);
				Dimension size = getSize();
				Graphics2D g2 = (Graphics2D)g;
				RenderingHints rh =
		            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
		                               RenderingHints.VALUE_ANTIALIAS_ON);

				rh.put(RenderingHints.KEY_RENDERING,
						RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHints(rh);
				g2.setColor(new Color(0xaaffaa));
				g2.fillOval(4, 4, 12, 12);
				g2.setColor(new Color(0x222222));
				g2.drawOval(4, 4, 12, 12);
				if (isSelected()){
					g2.fillOval(7, 7, 7, 7);
				}
				g2.setColor(Color.green);
				g2.drawString(getText(), 24, 15);
				
			}
			
		};
		button.setOpaque(false);
		button.setActionCommand(title);
		return button;
	}
	/**
	 * @param listModel
	 * @return JList with listModel.
	 */
	public static JList createList(ListModel listModel){
		JList list = new JList(listModel);
		list.setBackground(new Color(0xaaffaa));
		list.setCellRenderer(new GameListCellRender());
		return list;
	}
}
