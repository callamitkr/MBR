package com.capgemini.mbr.report.util;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.sl.usermodel.TextShape.TextAutofit;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;

import com.capgemini.mbr.report.model.Report;

@Configuration
@PropertySource("classpath:messages.properties")
public class PptGenerator {

	@Value("${report.main.title}")
	private  String mainReportTitle;
	@Value("${report.name}")
	private  String reportName;
	@Value("${report.name.font}")
	private  String reportNameFont;
	@Value("${report.table.title}")
	private  String reportTableTitle;
	@Value("${column.programName}")
	private  String programName;
	@Value("${column.projectDescription}")
	private  String projectDescription;
	@Value("${column.barclaysPm}")
	private  String barclaysPm;
	@Value("${column.bu}")
	private  String bu;
	@Value("${column.phase}")
	private  String phase;
	@Value("${column.keyMilestone}")
	private  String keyMilestone;
	@Value("${column.KeyHighlights}")
	private  String KeyHighlights;
	@Value("${column.barclaysFeedback}")
	private  String barclaysFeedback;
	@Value("${column.issue}")
	private  String issue;
	@Value("${title.head.font}")
	private  String titleHeadFont;
	@Value("${table.font}")
	private  String tableFont;
	@Value("${table.title.font.size}")
	private  Double tableTitleFontSize;
	@Value("${title.font.size}")
	private  Double titleFontSize;
	@Value("${report.name.font.size}")
	private  Double reportNameFontSize;
	@Value("${table.font.size}")
	private  Double tableFontSize;
	@Autowired
	private DateUtil dateUtil;
	private XSLFSlideMaster slideMaster;
	private XSLFSlide slide1,slide2;
	private XSLFSlideLayout titleLayout,slidelayoutTitleOnly;
	private XSLFTextRun textRunTitle1,textRunTitle2,slid2TitleTextRun,row;
	private XSLFTextShape slide1Title1,slide1Title2,slid2Title;
	private XSLFPictureData pictureData;
	private XSLFTable table; 
	private XSLFTableRow tableRow,tr;
	private XSLFTextParagraph paragraph;
	private XSLFTableCell cell;
	private File image;
	private XSLFAutoShape shapeLine,shapeBorder;
	private byte[] picture ;

	public  ByteArrayInputStream generatePpt(List<Report> reportList) throws IOException {

		String[] columns = { programName, projectDescription, barclaysPm, bu , phase,
								keyMilestone, KeyHighlights,barclaysFeedback,issue};
				
		
		
		try (XMLSlideShow ppt = new XMLSlideShow(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	
			slideMaster = ppt.getSlideMasters().get(0);
		    titleLayout = slideMaster.getLayout(SlideLayout.TITLE);
		    slide1 = ppt.createSlide(titleLayout);
			slide1Title1 = slide1.getPlaceholder(0);
			shapeLine = slide1.createAutoShape();
			shapeLine.setAnchor(new Rectangle(35, 290, 650, 3));
			shapeLine.setShapeType(ShapeType.LINE);
			shapeLine.setLineColor(new Color(201, 201, 100));
			shapeLine.setLineWidth(2);
			
			shapeBorder = slide1.createAutoShape();
			shapeBorder.setAnchor(new Rectangle(15, 15, 690, 510));
			shapeBorder.setShapeType(ShapeType.RECT);
			shapeBorder.setLineColor(new Color(201, 201, 100));
			shapeBorder.setLineWidth(2);
			
			slide1Title1.clearText();
		    textRunTitle1 = slide1Title1.addNewTextParagraph().addNewTextRun();
			textRunTitle1.setFontColor(Color.RED);
			textRunTitle1.setFontSize(titleFontSize);
			textRunTitle1.setText(mainReportTitle);
			textRunTitle1.setFontFamily(titleHeadFont);

			slide1Title2 = slide1.getPlaceholder(1);
			slide1Title2.clearText();
		    textRunTitle2 = slide1Title2.addNewTextParagraph().addNewTextRun();
			textRunTitle2.setFontSize(reportNameFontSize);
			textRunTitle2.setText(reportName+" - "+ dateUtil.getMontYearPattern(07,2020,"MMM yyyy"));
			textRunTitle2.setFontFamily(reportNameFont);
			textRunTitle2.setFontColor(Color.BLUE);

			// reading an image
			image = ResourceUtils.getFile("classpath:image/thumbup.png");
		    picture = IOUtils.toByteArray(new FileInputStream(image));
						
			slidelayoutTitleOnly = slideMaster.getLayout(SlideLayout.TITLE_ONLY);
		    slide2 = ppt.createSlide(slidelayoutTitleOnly);
			slid2Title = slide2.getPlaceholder(0);
			slid2Title.setAnchor(new Rectangle(0, 0, 720, 50));
			slid2Title.setTextAutofit(TextAutofit.NORMAL);
			slid2Title.clearText();

		    pictureData = ppt.addPicture(picture, PictureType.PNG);
			slid2Title.setFillColor(new Color(201, 201, 191));
			slid2Title.getSheet().createPicture(pictureData).setAnchor(new Rectangle(50,5,40,40));
			slid2Title.getSheet().createPicture(pictureData).setAnchor(new Rectangle(630,5,40,40));
			slid2Title.setAnchor(new Rectangle(0, 0, 720, 50));
		    slid2TitleTextRun = slid2Title.addNewTextParagraph().addNewTextRun();
		    slid2TitleTextRun.setFontSize(tableTitleFontSize);
		    slid2TitleTextRun.setFontColor(Color.white);
		    slid2TitleTextRun.setFontFamily(titleHeadFont);
		    slid2TitleTextRun.setText(reportTableTitle);
		    
			table = slide2.createTable();
			table.setAnchor(new Rectangle(0, 50, 720, 300));	
			tableRow = table.addRow();
			tableRow.setHeight(50);
			for (int i = 0; i < columns.length; i++) {
				XSLFTableCell th = tableRow.addCell();
				 paragraph = th.addNewTextParagraph();
				 paragraph.setTextAlign(TextAlign.CENTER);
				 row = paragraph.addNewTextRun();
				 row.setText(columns[i]);
				 row.setFontSize(tableFontSize);
				 row.setFontColor(Color.white);
				 row.setFontFamily(tableFont);
				 row.setBold(true);
				 th.setFillColor(new Color(149, 183, 72));
				 table.setColumnWidth(i, 80);
			}

			for (int rownum = 0; rownum < reportList.size(); rownum++) {
				tr = table.addRow();
				tr.setHeight(50);
				 
				for (int i = 0; i < columns.length; i++) {
				    cell = tr.addCell();
					paragraph = cell.addNewTextParagraph();
					paragraph.setTextAlign(TextAlign.CENTER);
					row = paragraph.addNewTextRun();
					row.setFontFamily(tableFont);
					row.setFontSize(tableFontSize);
					switch (i) {
					case 0:
						 row.setText(reportList.get(rownum).getProject().getProjectName());
						break;
					case 1:
						row.setText(reportList.get(rownum).getProject().getProjectDesc());
						break;
					case 2:
						row.setText(reportList.get(rownum).getProject().getBarclaysPm());
						break;
					case 3:
						row.setText(reportList.get(rownum).getProject().getBu().getBu());
						break;
					case 4:
						row.setText(reportList.get(rownum).getProject().getPhase().getPhase());
						break;
					case 5:
						row.setText(reportList.get(rownum).getProjectStatus().getStatus());
						break;
					case 6:
						row.setText(reportList.get(rownum).getKeyHighlights());
						break;
					case 7:
						row.setText(reportList.get(rownum).getBarclaysFeedback());
						break;
					case 8:
						row.setText(reportList.get(rownum).getIssueRoadblock());
						break;
					}
					
					if (rownum % 2 == 0) {
						cell.setFillColor(new Color(208, 216, 232));
					} else {
						cell.setFillColor(new Color(233, 247, 244));
					}
				}
			}

			ppt.write(out);
			out.close();
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}
