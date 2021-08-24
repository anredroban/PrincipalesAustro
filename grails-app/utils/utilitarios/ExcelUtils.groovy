package utilitarios

import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableCell
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableSheet

/**
 * Created by cedancp on 3/21/17.
 */
class ExcelUtils {

    static void addCells(String[] values, WritableSheet sheet, int row,
                                Colour background, Alignment alignment,
                                VerticalAlignment vAlignment,
                                WritableFont cellFont,
                                Border border,
                                BorderLineStyle borderStyle){
        for(int i = 0; i < values.length; i++){
            addCell(values[i], sheet, i, row, background, alignment, vAlignment, cellFont, border, borderStyle)
        }

    }

    static void addCell(String value, WritableSheet sheet, int col, int row,
                         Colour background, Alignment alignment,
                         VerticalAlignment vAlignment,
                         WritableFont cellFont,
                         Border border,
                         BorderLineStyle borderStyle){

        WritableCell cell = new Label(col, row, value)

        WritableCellFormat cellFormat = new WritableCellFormat()
        cellFormat.setBackground(background)
        cellFormat.setAlignment(alignment)
        if(vAlignment != null) {
            cellFormat.setVerticalAlignment(vAlignment)
        }
        if(cellFont != null) {
            cellFormat.setFont(cellFont)
        }
        if(border == null){
            cellFormat.setBorder(Border.ALL,BorderLineStyle.HAIR,Colour.GRAY_25)
        }else{
            cellFormat.setBorder(border,borderStyle)
        }

        cell.setCellFormat(cellFormat)
        sheet.addCell(cell)
    }

    static void addCell(String value,WritableSheet sheet,int col,int row,
                         Colour colour, Alignment alignment,
                         VerticalAlignment vAlignment,
                         WritableFont cellFont){

        WritableCell cell = new Label(col, row, value)

        WritableCellFormat cellFormat = new WritableCellFormat()
        cellFormat.setBackground(colour)
        cellFormat.setAlignment(alignment)
        if(vAlignment != null) {
            cellFormat.setVerticalAlignment(vAlignment)
        }
        if(cellFont != null) {
            cellFormat.setFont(cellFont)
        }

        cell.setCellFormat(cellFormat)
        sheet.addCell(cell)
    }

    static WritableFont getCellFont(WritableFont.FontName font,int size,Colour colour,boolean bold){
        WritableFont cellFont = new WritableFont(font, size)
        cellFont.setColour(colour)
        if(bold){
            cellFont.setBoldStyle(WritableFont.BOLD)
        }
        return cellFont
    }

}
