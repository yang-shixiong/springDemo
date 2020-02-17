package com.yang.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.result.Row;
import com.yang.domain.*;
import com.yang.service.EmployeeService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    /*注入业务层*/
    @Autowired
    private EmployeeService employeeService;

    /*返回employee的jsp文件*/
    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }

    /*返回员工列表*/
    @RequestMapping("/employee/list")
    @ResponseBody  // 返回json数据
    public PageListRes employeeList(QueryVo queryVo) {
        return employeeService.getList(queryVo);
    }

    /*增加员工*/
    @RequestMapping("/employee/add")
    @ResponseBody
    public AjaxRes employeeAdd(Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    /*编辑员工*/
    @RequestMapping("/employee/update")
    @ResponseBody
    @RequiresPermissions("employee:update")  // 配置权限
    public AjaxRes employeeUpdate(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    /*离职操作*/
    @RequestMapping("/employee/state")
    @ResponseBody
    public AjaxRes employeeUpdate(Integer id) {
        return employeeService.changeState(id);
    }

    /*
        设置异常处理
        参数method就是发生异常的方法
    */
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws Exception {
        /*如果授权异常，则跳转授权页面*/
        // 获取异常方法中的是否是json请求
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null) {
            // 这个就是ajax的请求
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("没有权限操作");
            String res = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(res);
        } else {
            response.sendRedirect("error-permission.jsp");
        }
    }

    /*处理下载*/
    @RequestMapping("/employee/download")
    @ResponseBody
    public void downLoadEmployee(HttpServletResponse response) {
        // 首先获取所有的用户
        List<Employee> employees = employeeService.getAll();
        try {
            // 初始化一个工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 新建一个sheet表
            HSSFSheet sheet = workbook.createSheet("员工数据");
            // 设置头部的行
            HSSFRow row = sheet.createRow(0);
            // 为每行设置字段
            row.createCell(0).setCellValue("编码");
            row.createCell(1).setCellValue("用户名");
            row.createCell(2).setCellValue("入职时间");
            row.createCell(3).setCellValue("电话");
            row.createCell(4).setCellValue("邮箱");
            row.createCell(5).setCellValue("是否在职");
            row.createCell(6).setCellValue("是否是管理员");

            // 开始设置信息
            // 不再循环中每次都初始化一行，在外面初始化
            HSSFRow employeeRow = null;
            for (int i = 0; i < employees.size(); i++) {
                // 获取一个对象
                Employee employee = employees.get(i);
                // 创建一行
                employeeRow = sheet.createRow(i + 1);
                employeeRow.createCell(0).setCellValue(employee.getId());
                employeeRow.createCell(1).setCellValue(employee.getUsername());
                // 入职日期是时间格式，转化为字符串格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                String hireData = simpleDateFormat.format(employee.getHiredate());
                // 设置入职日期
                employeeRow.createCell(2).setCellValue(hireData);
                employeeRow.createCell(3).setCellValue(employee.getPhone());
                employeeRow.createCell(4).setCellValue(employee.getEmail());
                employeeRow.createCell(5).setCellValue(employee.getState() ? "是" : "否");
                employeeRow.createCell(6).setCellValue(employee.getAdmin() ? "是" : "否");
            }
            // 响应给浏览器
            // 设置文件名,iso8859-1需要设置
            String fineName = new String("员工数据.xls".getBytes(StandardCharsets.UTF_8), "iso8859-1");
            // 设置响应头为附件
            response.setHeader("content-Disposition", "attachment;filename=" + fineName);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*处理下载员工模版*/
    @RequestMapping("/employee/download/template")
    @ResponseBody
    public void DownloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        // 先声明一个文件流，最后关闭
        FileInputStream inputStream = null;
        // 获取文件路径
        String realPath = request.getSession().getServletContext().getRealPath("static/employeeTemp.xls");
        try {
            // 读取文件
            inputStream = new FileInputStream(realPath);
            // 响应给浏览器
            // 设置文件名,iso8859-1需要设置
            String fineName = new String("employeeTemp.xls".getBytes(StandardCharsets.UTF_8), "iso8859-1");
            // 设置响应头为附件
            response.setHeader("content-Disposition", "attachment;filename=" + fineName);
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 如果文件流不为空，则关闭
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*进行文件上传*/
    @RequestMapping("/employee/upload/file")
    @ResponseBody
    public AjaxRes uploadEmployee(MultipartFile excel) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 将上传的文件作为流传入
            HSSFWorkbook workbook = new HSSFWorkbook(excel.getInputStream());
            // 获取第一张工作表，因为模版中只规定创建第一张工作表
            HSSFSheet sheet = workbook.getSheetAt(0);
            // 获取最大行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow employeeRow = null;
            for (int i = 1; i <= lastRowNum; i++) {
                Employee employee = new Employee();
                employeeRow = sheet.getRow(i);
                // 设置用户名
                employee.setUsername((String) getCellValue(employeeRow.getCell(1)));
                // 设置入职时间
                employee.setHiredate((Date) getCellValue(employeeRow.getCell(2)));
                // 设置电话，因为11为返回的数字是double，科学技术，我们选转化为BigDecimal然后再转化为字符串
                double phone = (double) getCellValue(employeeRow.getCell(3));
                BigDecimal phoneDecimal = new BigDecimal(String.valueOf(phone));
                employee.setPhone(phoneDecimal.toPlainString());
                // 设置邮箱
                employee.setEmail((String) getCellValue(employeeRow.getCell(4)));
                // 设置是否在职
                String employeeState = (String) getCellValue(employeeRow.getCell(5));
                employee.setState(employeeState.equals("否"));
                // 设置是否是管理员
                String admin = (String) getCellValue(employeeRow.getCell(6));
                employee.setAdmin(admin.equals("是"));
                // 设置初始密码 1234
                employee.setPassword("1234");
                employeeService.insertEmployeeInExcel(employee);
                ajaxRes.setSuccess(true);
                ajaxRes.setMsg("导入成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("导入失败！");
        }
        return ajaxRes;
    }

    /*设置处理每一行类型的函数*/
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                // 判断是否时间
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;
    }
}
