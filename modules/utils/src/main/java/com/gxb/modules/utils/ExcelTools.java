package com.gxb.modules.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelTools {
	private static final Logger LOG = LoggerFactory.getLogger(ExcelTools.class);

	public Workbook parseFile(String suffix, InputStream is) {
		Workbook workBook = null;
		try {
			if (".xls".equals(suffix)) { // 97-03
				workBook = new HSSFWorkbook(is);
			} else if (".xlsx".equals(suffix)) { // 2007
				workBook = new XSSFWorkbook(is);
			} else {
				LOG.error("解析excel时，不支持的文件类型");
				return null;
			}
		} catch (Exception e) {
			LOG.error("解析xls文件出错", e);
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				LOG.error("关闭excel文件流异常", e);
			}
		}
		return workBook;
	}

	public static String getContent(Row row, int k) {
		String content = null;
		Cell cell = row.getCell(k);
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING); // 全部置成String类型的单元格
			content = cell.getStringCellValue().replaceAll("\\s*|\t|\r|\n", "");
		}
		return content;
	}

	private boolean checkSheetNumber(Workbook workBook, int readSheetNumber) {
		int sheetNumber = null != workBook ? workBook.getNumberOfSheets() : 0;
		if (sheetNumber < 1 || readSheetNumber > sheetNumber) {
			LOG.error("当前工作表的数量异常");
			return true;
		}
		return false;
	}

	public List<List<String[]>> readStudentExcel(Workbook workBook, int readSheetNumber) {
		List<List<String[]>> excelSheets = new ArrayList<>();
		try {
			if (checkSheetNumber(workBook, readSheetNumber)) {
				return null;
			}
			for (int i = 0; i < readSheetNumber; i++) {
				Sheet sheet = workBook.getSheetAt(i); // 读取第一个sheet
				int rowNumber = sheet.getPhysicalNumberOfRows(); // 获得行数
				List<String[]> sheetList = new ArrayList<>();

				// 默认第一行是标题名，检查格式是否正确
				Row titleRow = sheet.getRow(0);
				int titleRowCellNumber = titleRow.getLastCellNum();// 获得列数
				if (titleRowCellNumber >= 9) {
					String noTitle = getContent(titleRow, 0);
					if (!"学号".equals(noTitle)) {
						return null;
					}
					String nameTitle = getContent(titleRow, 1);
					if (!"真实姓名".equals(nameTitle)) {
						return null;
					}

					String mobileTitle = getContent(titleRow, 2);
					if (!"手机号".equals(mobileTitle)) {
						return null;
					}

					String emailTitle = getContent(titleRow, 3);
					if (!"邮箱".equals(emailTitle)) {
						return null;
					}

					String genderTitle = getContent(titleRow, 4);
					if (!"性别".equals(genderTitle)) {
						return null;
					}
					String schoolDepartmentTitle = getContent(titleRow, 5);
					if (!"院系名称".equals(schoolDepartmentTitle)) {
						return null;
					}
					String majorTitle = getContent(titleRow, 6);
					if (!"专业".equals(majorTitle)) {
						return null;
					}
					String schoolGradeTitle = getContent(titleRow, 7);
					if (!"年级".equals(schoolGradeTitle)) {
						return null;
					}
					String schoolClassTitle = getContent(titleRow, 8);
					if (!"班级".equals(schoolClassTitle)) {
						return null;
					}

				} else {
					return null;
				}
				for (int j = 1; j < rowNumber; j++) {
					Row row = sheet.getRow(j);
					if (row == null) {
						continue;
					}
					int cellNumber = row.getLastCellNum();// 获得列数
					int contentSize = cellNumber > 9 ? cellNumber : 9;
					String[] contentArr = new String[contentSize];
					Boolean isAdd = false;
					for (int k = 0; k < cellNumber; k++) {
						String content = getContent(row, k);
						contentArr[k] = StringTools.isBlank(content) ? null : content;
						if (!isAdd && contentArr[k] != null) {
							isAdd = true;
						}
					}
					if (isAdd) {
						sheetList.add(contentArr);
					}
				}
				excelSheets.add(sheetList);
			}
		} catch (Exception ex) {
			LOG.error("read student user excel error", ex);
		}
		return excelSheets;
	}


	public List<List<String[]>> readClassGroupUserExcel(Workbook workBook, int readSheetNumber) {
		List<List<String[]>> excelSheets = new ArrayList<>();
		try {
			if (checkSheetNumber(workBook, readSheetNumber)) {
				return null;
			}
			for (int i = 0; i < readSheetNumber; i++) {
				Sheet sheet = workBook.getSheetAt(i); // 读取第一个sheet
				int rowNumber = sheet.getPhysicalNumberOfRows(); // 获得行数
				List<String[]> sheetList = new ArrayList<>();

				// 默认第一行是标题名，检查格式是否正确
				Row titleRow = sheet.getRow(0);
				int titleRowCellNumber = titleRow.getLastCellNum();// 获得列数
				if (titleRowCellNumber >= 3) {
					String noTitle = getContent(titleRow, 0);
					if (!"学号".equals(noTitle)) {
						return null;
					}
					String nameTitle = getContent(titleRow, 1);
					if (!"真实姓名".equals(nameTitle)) {
						return null;
					}

					String teamNameTitle = getContent(titleRow, 2);
					if (!"班组".equals(teamNameTitle)) {
						return null;
					}
				} else {
					return null;
				}
				for (int j = 1; j < rowNumber; j++) {
					Row row = sheet.getRow(j);
					if (row == null) {
						continue;
					}
					int cellNumber = row.getLastCellNum();// 获得列数
					int contentSize = cellNumber > 3 ? cellNumber : 3;
					String[] contentArr = new String[contentSize];
					Boolean isAdd = false;
					for (int k = 0; k < cellNumber; k++) {
						String content = getContent(row, k);
						contentArr[k] = StringTools.isBlank(content) ? null : content;
						if (!isAdd && contentArr[k] != null) {
							isAdd = true;
						}
					}
					if (isAdd) {
						sheetList.add(contentArr);
					}
				}
				excelSheets.add(sheetList);
			}
		} catch (Exception ex) {
			LOG.error("read student user excel error", ex);
		}
		return excelSheets;
	}

    private Boolean checkTitle(Row titleRow){
        int titleRowCellNumber = titleRow.getLastCellNum();// 获得列数
        if (titleRowCellNumber < 7 || titleRowCellNumber > 13) {//至少是7列,最多11列
            return false;
        }
        String typeTitle = ExcelTools.getContent(titleRow, 0);
        if (!"类型（必填）".equals(typeTitle)) {
            return false;
        }
        String nameTitle = ExcelTools.getContent(titleRow, 1);
        if (!"标题（必填）".equals(nameTitle)) {
            return false;
        }
        String ATitle = ExcelTools.getContent(titleRow, 2);
        if (!"A（必填）".equals(ATitle)) {
            return false;
        }
        String BTitle = ExcelTools.getContent(titleRow, 3);
        if (!"B（必填）".equals(BTitle)) {
            return false;
        }
        String levelTitle = ExcelTools.getContent(titleRow, titleRowCellNumber-1);
        if (!"难度（必填）".equals(levelTitle)) {
            return false;
        }
        String commentTitle = ExcelTools.getContent(titleRow, titleRowCellNumber-2);
        if (!"解析（必填）".equals(commentTitle)) {
            return false;
        }
        String answerTitle = ExcelTools.getContent(titleRow, titleRowCellNumber-3);
        if (!"答案（必填）".equals(answerTitle)) {
            return false;
        }
        return true;
    }

    public List<List<String[]>> readQuestionExcel(Workbook workBook) {
        int readSheetNumber = workBook.getNumberOfSheets();
        List<List<String[]>> excelSheets = new ArrayList<>();
        try {
            for (int i = 0; i < readSheetNumber; i++) {
                Sheet sheet = workBook.getSheetAt(i); // 读取一个sheet

                int rowNumber = sheet.getPhysicalNumberOfRows();
                if (rowNumber==0){
                    continue;
                }

                Row titleRow = sheet.getRow(0);//第一行

                if (!checkTitle(titleRow)){//判断标题行是否正确
                    continue;
                }

                List<String[]> sheetList = new ArrayList<>();

                // 默认第一行是标题名，检查格式是否正确
                int titleRowCellNumber = titleRow.getLastCellNum();// 获得列数
                for (int j = 1; j < rowNumber; j++) {
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    String[] contentArr = new String[titleRowCellNumber];
                    Boolean isAdd = false;
                    for (int k = 0; k < titleRowCellNumber; k++) {
                        String content = getContent(row, k);
                        contentArr[k] = StringTools.isBlank(content) ? "" : content;
                        if (!isAdd) {
                            isAdd = true;
                        }
                    }
                    if (isAdd) {
                        sheetList.add(contentArr);
                    }
                }
                excelSheets.add(sheetList);
            }
        } catch (Exception ex) {
            LOG.error("read student user excel error", ex);
        }
        return excelSheets;
    }
	/**
	 * 标题单元格样式
	 */
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {
		// 普通数据单元格样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 普通数据单元格字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		titleFont.setFontName("Microsoft YaHei");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBold(true);
		// 把字体应用到当前的样式
		titleStyle.setFont(titleFont);
		return titleStyle;
	}

	/**
	 * 标题单元格样式
	 */
	private static HSSFCellStyle createHeadStyle(HSSFWorkbook workbook) {
		// 标题单元格样式
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 标题单元格字体
		HSSFFont headFont = workbook.createFont();
		headFont.setFontHeightInPoints((short) 12);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setBold(true);

		// 把字体应用到当前的样式
		headStyle.setFont(headFont);
		return headStyle;
	}

    private static HSSFCellStyle createErrorHeadStyle(HSSFWorkbook workbook) {
        // 标题单元格样式
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 标题单元格字体
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headFont.setBold(true);
        headFont.setColor(Font.COLOR_RED);

        // 把字体应用到当前的样式
        headStyle.setFont(headFont);
        return headStyle;
    }

	/**
	 * 普通数据单元格样式
	 */
	private static HSSFCellStyle createCommonDataStyle(HSSFWorkbook workbook) {
		// 普通数据单元格样式
		HSSFCellStyle commonDataStyle = workbook.createCellStyle();
		commonDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		commonDataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		commonDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		commonDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		commonDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		commonDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		commonDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		commonDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 普通数据单元格字体
		HSSFFont commonDataFont = workbook.createFont();
		commonDataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		commonDataFont.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		commonDataStyle.setFont(commonDataFont);
		return commonDataStyle;
	}

    /**
     * 错误提示单元格样式
     */
    private static HSSFCellStyle createErrorDataStyle(HSSFWorkbook workbook) {
        // 普通数据单元格样式
        HSSFCellStyle commonDataStyle = workbook.createCellStyle();
        commonDataStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        commonDataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        commonDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        commonDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 普通数据单元格字体
        HSSFFont commonDataFont = workbook.createFont();
        commonDataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        commonDataFont.setFontHeightInPoints((short) 12);
        commonDataFont.setColor(Font.COLOR_RED);
        // 把字体应用到当前的样式
        commonDataStyle.setFont(commonDataFont);
        return commonDataStyle;
    }


	public static File wirteExcel(String titleName, String[] columnName, List<String[]> values, String filePath) {

		FileOutputStream fos = null;
		HSSFWorkbook workbook = null;
		try {

			// 获取总列数
			int countColumnNum = columnName.length;

			// 创建Excel文档
			workbook = new HSSFWorkbook();

			HSSFCellStyle titleStyle = createTitleStyle(workbook); // 生成标题样式
			HSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式
			HSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式

			// sheet 对应一个工作页
			HSSFSheet sheet = workbook.createSheet("工作表");
			sheet.setDefaultColumnWidth(15);

			// 行索引
			int rowNumber = 0;

			// 创建标题行
			if (StringTools.isNotBlank(titleName)) {
				HSSFRow row = sheet.createRow(rowNumber);
				HSSFCell cell = row.createCell(0);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(titleName);
				// 合并标题行
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnName.length - 1));
				rowNumber++;
			}
            int errorTipCellIndex = -1;
			// 创建列名行
			HSSFRow headRow = sheet.createRow(rowNumber);
			for (int j = 0; j < countColumnNum; j++) {
				HSSFCell cell = headRow.createCell(j);
				cell.setCellValue(new HSSFRichTextString(columnName[j]));
                if ("错误提示".equals(columnName[j])){
                    cell.setCellStyle(createErrorHeadStyle(workbook));
                    errorTipCellIndex = j;
                }else{
                    cell.setCellStyle(headStyle);
                }
			}
			rowNumber++;

			// 在一行内循环
			for (int i = 0; i < values.size(); i++) {
				HSSFRow row = sheet.createRow(i + rowNumber);
				String[] strs = values.get(i);
				for (int j = 0; j < strs.length; j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(strs[j]);
                    if (errorTipCellIndex!=-1 && j==errorTipCellIndex){
                        cell.setCellStyle(createErrorDataStyle(workbook));
                    }else{
                        cell.setCellStyle(commonDataStyle);
                    }
				}
			}

			boolean canWrite = false;
			File file = new File(filePath);
			File parentFile = file.getParentFile();

			if (parentFile != null && !parentFile.exists()) {
				boolean mkdirs = parentFile.mkdirs();
				if (mkdirs) {
					canWrite = true;
				}
			} else if (parentFile != null && parentFile.exists()) {
				canWrite = true;
			}

			if (canWrite) {
				fos = new FileOutputStream(filePath);
				workbook.write(fos);
				fos.flush();

				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				if (workbook != null) {
					try {
						workbook.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				return file;
			} else {
				LOG.debug("excel can not wirte");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static File wirteClassQuizExcel(Map<String, Object> map, String[] columnName, List<Map> values, OutputStream out) {

		FileOutputStream fos = null;
		HSSFWorkbook workbook = null;
		try {
			int allowedAttempts = StringTools.getInteger(map.get("allowedAttempts"));
			int countColumnNum;
			if(allowedAttempts > 1){
				// 获取总列数
				countColumnNum = columnName.length + allowedAttempts * 2;
			} else {
				countColumnNum = columnName.length;
			}


			// 创建Excel文档
			workbook = new HSSFWorkbook();

			HSSFCellStyle titleStyle = titleStyle(workbook); // 生成标题样式
			HSSFCellStyle headStyle = headStyle(workbook); // 生成头部样式
			HSSFCellStyle commonDataStyle = commonDataStyle(workbook); // 生成一般数据样式

			// sheet 对应一个工作页
			HSSFSheet sheet = workbook.createSheet("工作表");
			sheet.setDefaultColumnWidth(15);

			// 行索引
			int rowNumber = 0;

			// 创建标题行
			if (StringTools.isNotBlank(StringTools.getString(map.get("title")))) {
				HSSFRow row = sheet.createRow(rowNumber);
				HSSFCell cell = row.createCell(0);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(StringTools.getString(map.get("title")));
				// 合并标题行
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, countColumnNum - 1));
				rowNumber++;
			}

			// 创建辅导老师行
			if (StringTools.isNotBlank(StringTools.getString(map.get("teacher")))) {
				HSSFRow row = sheet.createRow(rowNumber);
				HSSFCell cell = row.createCell(0);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(StringTools.getString(map.get("teacher")));
				// 合并标题行
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, countColumnNum - 1));
				rowNumber++;
			}
			// 创建列名行
			HSSFRow headRow = sheet.createRow(rowNumber);
			for (int j = 0; j < columnName.length; j++) {
				HSSFCell cell = headRow.createCell(j);
				cell.setCellValue(new HSSFRichTextString(columnName[j]));
				// 合并行
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + 2, j, j));
				cell.setCellStyle(headStyle);
			}
			if(allowedAttempts > 1){
				int dex = columnName.length;
				HSSFCell cellee = headRow.createCell(dex);
				cellee.setCellValue(new HSSFRichTextString("历史得分"));
				// 合并行
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, columnName.length, countColumnNum - 1));
				cellee.setCellStyle(headStyle);
				rowNumber++;
				headRow = sheet.createRow(rowNumber);
				for(int i = 0; i < allowedAttempts; i++){
					cellee = headRow.createCell(dex);
					cellee.setCellValue(new HSSFRichTextString((i == 0) ? "第一次" : (i == 1) ? "第二次" : (i == 2) ? "第三次" : (i == 3) ? "第四次" : (i == 4) ? "第五次" : (i == 5) ? "第六次" : (i == 6) ? "第七次" :  (i == 7) ? "第八次" : ""));
					// 合并行
					sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, dex, dex + 1));
					cellee.setCellStyle(headStyle);
					dex = dex+2;
				}
				rowNumber++;
				headRow = sheet.createRow(rowNumber);
				dex = columnName.length;
				for(int i = 0; i < allowedAttempts * 2; i++){
					cellee = headRow.createCell(dex);
					cellee.setCellValue(new HSSFRichTextString((i%2==0) ? "提交时间" : "得分"));
					cellee.setCellStyle(headStyle);
					dex++;
				}
				rowNumber++;
			} else {
				rowNumber=rowNumber+3;
			}

			//在一行内循环
			if(null != values){
				for (int i = 0; i < values.size(); i++) {
					int colNum = 0;
					Map classQuizMap = values.get(i);
					HSSFRow row = sheet.createRow(i + rowNumber);
					HSSFCell cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("no")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("name")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("mobile")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("email")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("schoolClass")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("groupName")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue((classQuizMap.get("submittedAt") == null) ? null : DateTools.dateToStrLong(new Date(StringTools.getLong(classQuizMap.get("submittedAt")))));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue((classQuizMap.get("score") == null) ? "未提交" : StringTools.getString(classQuizMap.get("score")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					if(allowedAttempts > 1 && null != classQuizMap.get("submittedAt")){
						List<Map> quizSubmissionMapList = (List<Map>) classQuizMap.get("quizSubmissionMapList");
						for (int j = 0; j < quizSubmissionMapList.size(); j++) {
							Map quizSubmissonMap = quizSubmissionMapList.get(j);
							cell = row.createCell(colNum);
							cell.setCellValue((quizSubmissonMap.get("createdAt") == null) ? null : DateTools.dateToStrLong(new Date(StringTools.getLong(quizSubmissonMap.get("createdAt")))));
							cell.setCellStyle(commonDataStyle);
							colNum++;
							cell = row.createCell(colNum);
							cell.setCellValue(StringTools.getString(quizSubmissonMap.get("score")));
							cell.setCellStyle(commonDataStyle);
							colNum++;
						}
					}

				}
			}

			workbook.write(out);
			out.flush();

			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 标题单元格样式
	 */
	private static HSSFCellStyle titleStyle(HSSFWorkbook workbook) {
		// 普通数据单元格样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		titleStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 普通数据单元格字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		titleFont.setFontName("Microsoft YaHei");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBold(true);
		// 把字体应用到当前的样式
		titleStyle.setFont(titleFont);
		return titleStyle;
	}

	/**
	 * 标题单元格样式
	 */
	private static HSSFCellStyle headStyle(HSSFWorkbook workbook) {
		// 标题单元格样式
		HSSFCellStyle headStyle = workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 标题单元格字体
		HSSFFont headFont = workbook.createFont();
		headFont.setFontHeightInPoints((short) 12);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headFont.setBold(true);

		// 把字体应用到当前的样式
		headStyle.setFont(headFont);
		return headStyle;
	}

	/**
	 * 普通数据单元格样式
	 */
	private static HSSFCellStyle commonDataStyle(HSSFWorkbook workbook) {
		// 普通数据单元格样式
		HSSFCellStyle commonDataStyle = workbook.createCellStyle();
		commonDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		commonDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 普通数据单元格字体
		HSSFFont commonDataFont = workbook.createFont();
		commonDataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		commonDataFont.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		commonDataStyle.setFont(commonDataFont);
		return commonDataStyle;
	}

	public static File wirteAssignmentExcel(Map<String, Object> map, String[] columnName, List<Map> values, OutputStream out) {

		FileOutputStream fos = null;
		HSSFWorkbook workbook = null;
		try {

			// 获取总列数
			int countColumnNum = columnName.length;

			// 创建Excel文档
			workbook = new HSSFWorkbook();

			HSSFCellStyle titleStyle = titleStyle(workbook); // 生成标题样式
			HSSFCellStyle headStyle = headStyle(workbook); // 生成头部样式
			HSSFCellStyle commonDataStyle = commonDataStyle(workbook); // 生成一般数据样式

			// sheet 对应一个工作页
			HSSFSheet sheet = workbook.createSheet("工作表");
			sheet.setDefaultColumnWidth(15);

			// 行索引
			int rowNumber = 0;

			// 创建标题行
			if (StringTools.isNotBlank(StringTools.getString(map.get("title")))) {
				HSSFRow row = sheet.createRow(rowNumber);
				HSSFCell cell = row.createCell(0);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(StringTools.getString(map.get("title")));
				// 合并标题行
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, countColumnNum - 1));
				rowNumber++;
			}

			// 创建辅导老师行
			if (StringTools.isNotBlank(StringTools.getString(map.get("teacher")))) {
				HSSFRow row = sheet.createRow(rowNumber);
				HSSFCell cell = row.createCell(0);
				cell.setCellStyle(titleStyle);
				cell.setCellValue(StringTools.getString(map.get("teacher")));
				// 合并标题行
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, countColumnNum - 1));
				rowNumber++;
			}
			// 创建列名行
			HSSFRow headRow = sheet.createRow(rowNumber);
			for (int j = 0; j < columnName.length; j++) {
				HSSFCell cell = headRow.createCell(j);
				cell.setCellValue(new HSSFRichTextString(columnName[j]));
				cell.setCellStyle(headStyle);
			}
			rowNumber++;
			//在一行内循环
			if(null != values){
				for (int i = 0; i < values.size(); i++) {
					int colNum = 0;
					Map classQuizMap = values.get(i);
					HSSFRow row = sheet.createRow(i + rowNumber);
					HSSFCell cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("no")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("name")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("mobile")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("email")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("schoolClass")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("groupName")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("gradedUser")));
					cell.setCellStyle(commonDataStyle);
					colNum++;
					cell = row.createCell(colNum);
					cell.setCellValue(StringTools.getString(classQuizMap.get("score")));
					cell.setCellStyle(commonDataStyle);
				}
			}
			workbook.write(out);
			out.flush();

			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	public static File wirteTeachingSuperviseExcel(String titleName, String[] columnName, List<String[]> values, String[] arr, OutputStream out) {

		HSSFWorkbook workbook = null;
		try {

			// 获取总列数
			int countColumnNum = columnName.length;

			// 创建Excel文档
			workbook = new HSSFWorkbook();

			HSSFCellStyle titleStyle = createTitleStyle(workbook); // 生成标题样式
			HSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式
			HSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式

			// sheet 对应一个工作页
			HSSFSheet sheet = workbook.createSheet("工作表");
			sheet.setDefaultColumnWidth(15);

			// 行索引
			int rowNumber = 0;

			// 创建标题行
			HSSFRow rowTitle = sheet.createRow(rowNumber);
			HSSFCell cellTitle = rowTitle.createCell(0);
			cellTitle.setCellStyle(titleStyle);
			cellTitle.setCellValue(titleName);
			// 合并标题行
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
			rowNumber++;

			int errorTipCellIndex = -1;
			HSSFSheet sheet2 = sheet;
			// 创建列名行
			HSSFRow headRow = sheet.createRow(rowNumber);
			for (int j = 0; j < countColumnNum; j++) {
				int temp = j;
				if(j >=7){
					j = j+8;
				}
				HSSFCell cell = headRow.createCell(j);
				cell.setCellValue(new HSSFRichTextString(columnName[temp]));
				cell.setCellStyle(headStyle);
				// 合并行
				sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber + 1, j, j));
				j = temp;
			}

			HSSFCell cell2 = headRow.createCell(7);
			cell2.setCellValue(new HSSFRichTextString("视频(总数"+arr[0]+"|"+arr[1]+"%)"));
			cell2.setCellStyle(headStyle);
			sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 7, 8));

			cell2 = headRow.createCell(9);
			cell2.setCellValue(new HSSFRichTextString("作业(总数"+arr[2]+"|"+arr[3]+"%)"));
			cell2.setCellStyle(headStyle);
			sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 9, 10));

			cell2 = headRow.createCell(11);
			cell2.setCellValue(new HSSFRichTextString("测验(总数"+arr[4]+"|"+arr[5]+"%)"));
			cell2.setCellStyle(headStyle);
			sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 11, 12));

			cell2 = headRow.createCell(13);
			cell2.setCellValue(new HSSFRichTextString("讨论(总数"+arr[6]+"|"+arr[7]+"%)"));
			cell2.setCellStyle(headStyle);
			sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 13, 14));

			//
			headRow = sheet.createRow(rowNumber+1);
			cell2 = headRow.createCell(7);
			cell2.setCellValue(new HSSFRichTextString("观看数"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(8);
			cell2.setCellValue(new HSSFRichTextString("得分"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(9);
			cell2.setCellValue(new HSSFRichTextString("提交数"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(10);
			cell2.setCellValue(new HSSFRichTextString("得分"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(11);
			cell2.setCellValue(new HSSFRichTextString("提交数"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(12);
			cell2.setCellValue(new HSSFRichTextString("得分"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(13);
			cell2.setCellValue(new HSSFRichTextString("提交数"));
			cell2.setCellStyle(headStyle);

			cell2 = headRow.createCell(14);
			cell2.setCellValue(new HSSFRichTextString("得分"));
			cell2.setCellStyle(headStyle);

			rowNumber = rowNumber+2;

			// 在一行内循环
			for (int i = 0; i < values.size(); i++) {
				HSSFRow row = sheet.createRow(i + rowNumber);
				String[] strs = values.get(i);
				for (int j = 0; j < strs.length; j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(strs[j]);
					if (errorTipCellIndex!=-1 && j==errorTipCellIndex){
						cell.setCellStyle(createErrorDataStyle(workbook));
					}else{
						cell.setCellStyle(commonDataStyle);
					}
				}
			}


				workbook.write(out);
				out.flush();

				if (out != null) {
					try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				if (workbook != null) {
					try {
						workbook.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {

			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
