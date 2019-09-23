package com.example.gpcodetemplate.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * @author gao peng
 * @date 2019/9/23 16:22
 */
@Controller
public class DemoController {

  @Autowired
  private Configuration configuration;

  @GetMapping(value = "/hello")
  @ResponseBody
  public void hello(
      HttpServletResponse response,
      Model model,
      @RequestParam(value = "name", required = false, defaultValue = "World") String name) throws Exception {
    Map<String, Object> params = new HashMap<String, Object>();


    params.put("author", "gao peng");
    params.put("date", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT));

    Template template = configuration.getTemplate("/codeTemplate/main.ftl");

    StringWriter sw = new StringWriter();
    template.process(params, sw);
    System.out.println(sw.toString());

    Writer writer = response.getWriter();
    writer.write(sw.toString());
    writer.close();
  }

}
