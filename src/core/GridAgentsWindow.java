package core;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * This class generates the window.
 * No further documentation.
 * 
 * @author Daniel Kottke, Georg Krempl
 */

@SuppressWarnings("serial")
public class GridAgentsWindow extends JFrame implements ActionListener{
	private AgentsProceed agentsProceed = null;
	
	private GridPanel gridpanel = null;
	private JButton bStep = null;
    private JButton bTrial = null;
    private JButton bTrials = null;
    
    public GridAgentsWindow(AgentsProceed agentsProceed){
    	this.agentsProceed = agentsProceed;
    	
        setTitle("Grid World"); 
//        setDefaultCloseOperation(exitButtonActionPerformed);
        
        this.gridpanel = new GridPanel(this.agentsProceed.getGridWorld(), this.agentsProceed.getAgentPosition()); 
        this.bStep = new JButton("1 Step");
        this.bStep.addActionListener(this);
        this.bTrial = new JButton("1 Trial");
        this.bTrial.addActionListener(this);
        this.bTrials = new JButton("10 Trials");
        this.bTrials.addActionListener(this);
        
        JPanel pane = new JPanel(new GridBagLayout());

        add(pane);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
    	c.weightx = 1;
        c.gridx = 0;
    	c.gridy = 1;
    	pane.add(bStep,c);
    	c.gridx = 1;
    	pane.add(bTrial,c);
    	c.gridx = 2;
    	pane.add(bTrials,c);
    	
    	c.gridwidth = 3;
    	c.gridx = 0;
    	c.gridy = 0;
    	c.weighty = 1;
    	pane.add(this.gridpanel,c);
        
        pack();
    	setSize(500,500);
        setVisible(true);
    }

    
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this.bStep){
			if (this.agentsProceed.doStep()){
				System.out.println(agentsProceed.printLastTrialEval());
			}
			this.gridpanel.setAgentsPosition(this.agentsProceed.getAgentPosition()); 
	        this.revalidate();
	        this.repaint();
		}
        if (ae.getSource() == this.bTrial){
	        new java.util.Timer().schedule(
	        		new java.util.TimerTask(){
			        	public void run(){
			        		runShowScheduleHelper();
			        	}
	        		},
	        		200);
		}
        if (ae.getSource() == this.bTrials){
        	for (int i = 0; i < 10; ++i){
				this.agentsProceed.doTrial();
				System.out.println(agentsProceed.printLastTrialEval());
        	}
			this.gridpanel.setAgentsPosition(this.agentsProceed.getAgentPosition()); 
	        this.revalidate();
	        this.repaint();
		}
        
	}
	
	private void runShowScheduleHelper(){
		boolean resetAgent = this.agentsProceed.doStep();

		this.gridpanel.setAgentsPosition(this.agentsProceed.getAgentPosition()); 
        this.revalidate();
        this.repaint();

        if (!resetAgent){
        	new java.util.Timer().schedule(
	        		new java.util.TimerTask(){
			        	public void run(){
			        		runShowScheduleHelper();
			        	}
	        		},
	        		200);
        }else{
        	System.out.println(agentsProceed.printLastTrialEval());
        }
	}
	
	public void processWindowEvent(WindowEvent arg) {
		if (arg.getID() == WindowEvent.WINDOW_CLOSING){
//			System.out.println(this.agentsProceed.getEvalVector()[0].toString());
			System.exit( 0 );
		}
	}

	
}
