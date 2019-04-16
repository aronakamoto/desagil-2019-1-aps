package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;
    private final JCheckBox outputBox;
    private final Image image;
    private Color black;
    private Color red;

    private Graphics graph;

    private boolean result;

    public GateView(Gate gate) {
        super(245, 346);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        outputBox = new JCheckBox();

        JLabel scaleLabel = new JLabel("                                                     ");

        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");

        black = Color.BLACK;
        red = Color.RED;

//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setLayout(null);

        add(scaleLabel, 0,0,50,25);

        int x = 15;
        int y = 40;

        for (int i=0; i<inputBoxes.length; i++){
            if (inputBoxes.length == 1){
                add(inputBoxes[i],x,50,50,25);
            }
            else{
                add(inputBoxes[i],x,(i*y)+30,50, 25 );
            }
        }

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        outputBox.setEnabled(false);

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

        boolean result = gate.read();
        this.result = result;

        outputBox.setSelected(result);
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 30, 15, 180, 100, this);


        if (this.result == false) {
            // Desenha um quadrado cheio.
            g.setColor(black);
            g.fillOval(155, 53,25,25);

        }
        else {
            g.setColor(red);
            g.fillOval(155, 53,25,25);

        }

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
        repaint();
    }
}
