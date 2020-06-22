package com.capgemini.mbr.util;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.capgemini.mbr.model.Report;
import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.sl.usermodel.TextShape.TextAutofit;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;

@Configuration
@PropertySource("classpath:messages.properties")
public class PptGenerator {
	@Value("${report.main.title}")
	private  String mainReportTitle;
	@Value("${report.sec.title}")
	private  String secReportTitle;
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
	@Value("${title.font.size}")
	private  Double titleFontSize;
	@Value("${table.font.size}")
	private  Double tableFontSize;
	@Value("${report.head.font.Size}")
	private  Double reportHeadFontSize;

	public  ByteArrayInputStream ReportToPpt(List<Report> reportList) throws IOException {

		String[] columns = { programName, projectDescription, barclaysPm, bu , phase,
								keyMilestone, KeyHighlights,barclaysFeedback,issue};
		XSLFSlideMaster slideMaster;
		XSLFSlide slide1,slide2;
		XSLFSlideLayout titleLayout,slidelayoutTitleOnly;
		XSLFTextRun textRunTitle1,textRunTitle2,slid2TitleTextRun,row;
		XSLFTextShape slide1Title1,slide1Title2,slid2Title;
		XSLFPictureData pictureData;
		XSLFTable table; 
		XSLFTableRow tableRow;
		XSLFTextParagraph paragraph;
		XSLFTableCell cell;
		XSLFTableRow tr;
		File image;
		byte[] picture ;
		
		try (XMLSlideShow ppt = new XMLSlideShow(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
		    slideMaster = ppt.getSlideMasters().get(0);
		    titleLayout = slideMaster.getLayout(SlideLayout.TITLE);
		    slide1 = ppt.createSlide(titleLayout);
			slide1Title1 = slide1.getPlaceholder(0);
			slide1Title1.clearText();
		    textRunTitle1 = slide1Title1.addNewTextParagraph().addNewTextRun();
			textRunTitle1.setFontColor(Color.RED);
			textRunTitle1.setFontSize(titleFontSize);
			textRunTitle1.setUnderlined(true);
			textRunTitle1.setText(mainReportTitle);
			textRunTitle1.setFontFamily(titleHeadFont);
		    slide1Title2 = slide1.getPlaceholder(1);
			slide1Title2.clearText();
		    textRunTitle2 = slide1Title2.addNewTextParagraph().addNewTextRun();
			textRunTitle2.setFontSize(titleFontSize);
			textRunTitle2.setText(secReportTitle);
			textRunTitle2.setFontFamily(titleHeadFont);
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
		    slid2TitleTextRun.setFontSize(reportHeadFontSize);
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
						 row.setText(reportList.get(rownum).getProjectName());
						break;
					case 1:
						row.setText(reportList.get(rownum).getProjectDesc());
						break;
					case 2:
						row.setText(reportList.get(rownum).getBarclaysPm());
						break;
					case 3:
						row.setText(reportList.get(rownum).getBu());
						break;
					case 4:
						row.setText(reportList.get(rownum).getPhase());
						break;
					case 5:
						row.setText(reportList.get(rownum).getKeyMilestone());
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
