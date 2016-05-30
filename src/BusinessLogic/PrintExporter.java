package BusinessLogic;

import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JTable;

import Models.Produs;

public class PrintExporter implements Exporter,Printable{

	@Override
	public void export(JTable tableProdus, List<Produs> listaProduse) {
		// TODO Auto-generated method stub
		MessageFormat header = new MessageFormat("COMANDA SIDE");
		
		PrinterJob printerJob=PrinterJob.getPrinterJob();

		Paper a4= new Paper();
		a4.setSize(590.936, 9200.536); 
		a4.setImageableArea(23, 23, 546, 1024);
		PageFormat format=new PageFormat();
		//format=printerJob.pageDialog(format);
		format.setPaper(a4);
		Book book=new Book();
		
		
		book.append(tableProdus.getPrintable(JTable.PrintMode.FIT_WIDTH, header, null), format);
		printerJob.setPageable(book);

		boolean doPrint=printerJob.printDialog();
		if (doPrint) {
		try {
		printerJob.print();
		} catch(PrinterException printerExc){}
		}
		
		
		
	}

	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}

}
