package edu.kis.powp.jobs2d.command.gui.transformations;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.TransformFeature;

public class TransformDriverAdapter implements Job2dDriver {

    private ILine line;
    private int startX = 0, startY = 0;
    private String name;
    private int shiftX = 0;
    private int shiftY = 0;

    private DrawPanelController drawController;

    public TransformDriverAdapter(DrawPanelController drawController, ILine line, String name) {
        super();
        this.drawController = drawController;
        this.line = line;
        this.name = name;
    }

    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void operateTo(int x, int y) {
        calculateShift();
        line.setStartCoordinates(this.startX + shiftX, this.startY + shiftY);
        this.setPosition(x, y);
        line.setEndCoordinates(x + shiftX, y + shiftY);
        drawController.drawLine(line);
    }

    @Override
    public String toString() {
        return "2d device simulator - " + name;
    }

    private void calculateShift(){
        int tempX = TransformFeature.getTransformManager().getTransformX();
        int tempY = TransformFeature.getTransformManager().getTransformY();
        double rotation = Math.toRadians(TransformFeature.getTransformManager().getRotation());
        shiftX = parseDoubleToInt((tempX) * Math.cos(rotation) - (tempY) * Math.sin(rotation));
        shiftY = parseDoubleToInt((tempX) * Math.sin(rotation) + (tempY) * Math.cos(rotation));
    }

    private int parseDoubleToInt(double number){
        return (int)number;
    }
}