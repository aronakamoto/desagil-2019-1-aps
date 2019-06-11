// FONTE DAS IMAGENS: https://en.wikipedia.org/wiki/Logic_gate (domínio público)

package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener {
    private static final int BORDER = 10;
    private static final int SWITCH_SIZE = 18;
    private static final int GATE_WIDTH = 90;
    private static final int GATE_HEIGHT = 60;

    private final Switch[] switches;
    private final Gate gate;
    private final JCheckBox[] inputBoxes;
    private final JCheckBox[] outputBoxes;

    private final Image image;

    public GateView(Gate gate) {
        super(BORDER + SWITCH_SIZE + GATE_WIDTH + SWITCH_SIZE + BORDER, GATE_HEIGHT);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        int outputeSize = gate.getOutputSize();

        outputBoxes = new JCheckBox[outputeSize];

        for (int j = 0; j < outputeSize; j++) {
            outputBoxes[j] = new JCheckBox();
        }

        int x, yinput, stepinput, yout, stepout;

        x = BORDER;
        yinput = -(SWITCH_SIZE / 2);
        stepinput = (GATE_HEIGHT / (inputSize + 1));
        for (JCheckBox inputBox : inputBoxes) {
            yinput += stepinput;
            add(inputBox, x, yinput, SWITCH_SIZE, SWITCH_SIZE);
        }

        yout = -(SWITCH_SIZE / 2);
        stepout = (GATE_HEIGHT / (outputeSize + 1));
        for (JCheckBox outputBox : outputBoxes) {
            yout += stepout;
            add(outputBox, BORDER + SWITCH_SIZE + GATE_WIDTH, yout, SWITCH_SIZE, SWITCH_SIZE);
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        for (JCheckBox outputBox : outputBoxes) {
            outputBox.setEnabled(false);
        }

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }


        for (int ind=0; ind <outputBoxes.length;ind++) {
            boolean result = gate.read(ind);
            outputBoxes[ind].setSelected(result);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, BORDER + SWITCH_SIZE, 0, GATE_WIDTH, GATE_HEIGHT, this);

        getToolkit().sync();
    }
}

//// FONTE DAS IMAGENS: https://en.wikipedia.org/wiki/Logic_gate (domínio público)
//
//package br.pro.hashi.ensino.desagil.aps.view;
//
//import br.pro.hashi.ensino.desagil.aps.model.Gate;
//import br.pro.hashi.ensino.desagil.aps.model.Switch;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.net.URL;
//
//public class GateView extends FixedPanel implements ItemListener {
//    private static final int BORDER = 10;
//    private static final int SWITCH_SIZE = 18;
//    private static final int GATE_WIDTH = 90;
//    private static final int GATE_HEIGHT = 60;
//
//    private final Switch[] switches;
//    private final Gate gate;
//    private final JCheckBox[] inputBoxes;
//    private final JCheckBox[] outputBoxes;
//    private final Image image;
//
//    public GateView(Gate gate) {
//        super(BORDER + SWITCH_SIZE + GATE_WIDTH + SWITCH_SIZE + BORDER, GATE_HEIGHT);
//
//        this.gate = gate;
//
//        int inputSize = gate.getInputSize();
//
//        switches = new Switch[inputSize];
//        inputBoxes = new JCheckBox[inputSize];
//
//        for (int i = 0; i < inputSize; i++) {
//            switches[i] = new Switch();
//            inputBoxes[i] = new JCheckBox();
//
//            gate.connect(i, switches[i]);
//        }
//
//        int outputSize = gate.getOutputSize();
//
//        outputBoxes = new JCheckBox[outputSize];
//
//        for (int j = 0; j < outputSize; j++) {
//            outputBoxes[j] = new JCheckBox();
//        }
//
//        int x, yi, stepi, yo, stepo;
//
//        x = BORDER;
//        yi = -(SWITCH_SIZE / 2);
//        stepi = (GATE_HEIGHT / (inputSize + 1));
//        for (JCheckBox inputBox : inputBoxes) {
//            yi += stepi;
//            add(inputBox, x, yi, SWITCH_SIZE, SWITCH_SIZE);
//        }
//
//        yo = -(SWITCH_SIZE / 2);
//        stepo = (GATE_HEIGHT / (outputSize + 1));
//        for (JCheckBox outputBox : outputBoxes) {
//            yo += stepo;
//            add(outputBox, BORDER + SWITCH_SIZE + GATE_WIDTH, yo, SWITCH_SIZE, SWITCH_SIZE);
//        }
//
//        String name = gate.toString() + ".png";
//        URL url = getClass().getClassLoader().getResource(name);
//        image = getToolkit().getImage(url);
//
//        for (JCheckBox inputBox : inputBoxes) {
//            inputBox.addItemListener(this);
//        }
//
//        for (JCheckBox outputBox : outputBoxes) {
//            outputBox.setEnabled(false);
//        }
//
//        update();
//    }
//
//    private void update() {
//        for (int i = 0; i < gate.getInputSize(); i++) {
//            if (inputBoxes[i].isSelected()) {
//                switches[i].turnOn();
//            } else {
//                switches[i].turnOff();
//            }
//        }
//
//        for (int ind = 0; ind < outputBoxes.length; ind++) {
//            boolean result = gate.read(ind);
//            outputBoxes[ind].setSelected(result);
//        }
//    }
//
//    @Override
//    public void itemStateChanged(ItemEvent event) {
//        update();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        g.drawImage(image, BORDER + SWITCH_SIZE, 0, GATE_WIDTH, GATE_HEIGHT, this);
//
//        getToolkit().sync();
//    }
//}
//
