package vista.componentes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.date.DateSelectionListener;

public class DialogoFecha extends JPanel{
	
	private boolean fechaDual;
	private String fechaN;
	private String[] fechasArreglo = new String[2];
	private DialogoRentar dialogoR;

	
	public DialogoFecha(boolean fechaDual, DialogoRentar dialogoR){
		setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(380, 340));
        setMaximumSize(new Dimension(380, 340));
        setLayout(null);
        this.fechaDual = fechaDual;
        JLabel texto = new JLabel();
        setOpaque(false);
        
        this.dialogoR = dialogoR;
        add(texto);
        
        selectorFecha();
	}
	
	public void selectorFecha(){
		if(fechaDual) {
			DatePicker fechaUsar = new DatePicker();
			JFormattedTextField editor = new JFormattedTextField();
			fechaUsar.setEditor(editor);
			fechaUsar.setDateSelectionMode(DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
			fechaUsar.setDateSelectionAble(localDate -> localDate.isAfter(LocalDate.now()));
			fechaUsar.setSeparator("   a   ");
			fechaUsar.setBounds(10,20,360, 320);
			fechaUsar.setBackground(Color.white);
			fechaUsar.addDateSelectionListener(new DateSelectionListener() {
				
				@Override
				public void dateSelected(DateEvent arg0) {
					LocalDate[] fechas = fechaUsar.getSelectedDateRange();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					if(fechas!=null) {
						System.out.println(formato.format(fechas[0])+ "  --  " + formato.format(fechas[1]));
						fechasArreglo[0] = formato.format(fechas[0]);
						fechasArreglo[1] = formato.format(fechas[1]);
						
						dialogoR.getTxtFechaRenta().setText(formato.format(fechas[0])+ "  --  " + formato.format(fechas[1]));
						dialogoR.getTxtFechaInicio().setText(formato.format(fechas[0]));
						dialogoR.getTxtFechaFinal().setText(formato.format(fechas[1]));
					}
				}
			});
			
			add(fechaUsar);
		}
		else{
			DatePicker fechaUsar = new DatePicker();
			JFormattedTextField editor = new JFormattedTextField();
			fechaUsar.setEditor(editor);
			fechaUsar.setDateSelectionAble(localDate -> !localDate.isAfter(LocalDate.of(2020, 12, 31)));
			fechaUsar.setSeparator("   a   ");
			fechaUsar.setBounds(10,20,360, 320);
			fechaUsar.setBackground(Color.white);
			fechaUsar.addDateSelectionListener(new DateSelectionListener() {
				
				@Override
				public void dateSelected(DateEvent arg0) {
					LocalDate fecha = fechaUsar.getSelectedDate();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					if(fecha!=null) {
						System.out.println(formato.format(fecha));
						
						dialogoR.getTxtFechaN().setText(formato.format(fecha));
					}
				}
			});
			
			add(fechaUsar);
		}
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),30,30));
		g2.dispose();
		super.paintComponent(g);
	}

	public String getFechaN() {
		return fechaN;
	}
	public void setFechaN(String fechaN) {
		this.fechaN = fechaN;
	}
	public String[] getFechasArreglo() {
		return fechasArreglo;
	}
	public void setFechasArreglo(String[] fechasArreglo) {
		this.fechasArreglo = fechasArreglo;
	}
}
