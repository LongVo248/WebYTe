package mtt.webyte.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mtt.webyte.dto.DoctorDTO;
import mtt.webyte.dto.ScheduleDTO;

public class Excel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DoctorDTO> users = new ArrayList<>();
    private List<Date> dates = new ArrayList<>();
   	Date from;
	Date to;

    public Excel(List<DoctorDTO> users, Date from, Date to) {
        workbook = new XSSFWorkbook();
	this.dates = getDaysBetweenDates(from, to);
	this.from = from;
	this.to = to;
	this.users = users;
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Doctor Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Mobile No.", style);

	for (int i = 4; i < dates.size() + 4; i++) {
        	createCell(row, i, dates.get(i-4).toString(), style);
	}
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (DoctorDTO record: users) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getUserId(), style);
            createCell(row, columnCount++, record.getUserFName(), style);
            createCell(row, columnCount++, record.getEmail(), style);
            createCell(row, columnCount++, record.getPhoneNum(), style);
	    for (int i = 0; i < this.dates.size(); i++) {
		String time = checkDate(record.getScheduleDTOs(), this.dates.get(i));
            	createCell(row, columnCount++, time, style);
	    }
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
    private List<Date> getFirstAndLastDateOfWeek( Date from, Date to) {
	// get today and clear time of day
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
	cal.clear(Calendar.MINUTE);
	cal.clear(Calendar.SECOND);
	cal.clear(Calendar.MILLISECOND);

	List<Date> dates = new ArrayList<>();


	// get start of this week in milliseconds
	cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
	dates.add(cal.getTime());

	for (int i = 0; i < 6; i++) {
		// start of the next week
		cal.add(Calendar.DATE, 1);
		dates.add(cal.getTime());
	}
	
	return dates;
    }
public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
{
    List<Date> dates = new ArrayList<Date>();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(startdate);

    while (calendar.getTime().before(enddate))
    {
        Date result = calendar.getTime();
        dates.add(result);
        calendar.add(Calendar.DATE, 1);
    }
    dates.add(enddate);
    return dates;
}

    private String checkDate(List<ScheduleDTO> scheduleDTOs, Date date) {
	    if(scheduleDTOs == null) {
		    return "-";
	    }
	for (ScheduleDTO schedule : scheduleDTOs) {
		if (schedule.getDayOfWeek().equals(date)) {
			return schedule.getStartTime() + "-" + schedule.getEndTime();
		}
	}	
	return "-";
    }
}
