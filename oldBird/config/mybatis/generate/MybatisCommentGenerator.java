package com.ecaicn.hms.model.util;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @author cheng_mboy
 * @create 2017-07-10-18:26
 */
public class MybatisCommentGenerator extends DefaultCommentGenerator {

    private boolean suppressFileComments = true;

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressFileComments) {
            StringBuffer sb = new StringBuffer();
            field.addJavaDocLine("/**");
            sb.append(" * 表字段 : ");
            //sb.append(introspectedTable.getFullyQualifiedTable());
            //sb.append('.');
            sb.append(introspectedColumn.getActualColumnName());
            field.addJavaDocLine(sb.toString());
            if (introspectedColumn.getRemarks() != null) {
                field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            }
            //addJavadocTag(field, false);
            field.addJavaDocLine(" */");
            sb = new StringBuffer();
            sb.append("@ApiModelProperty(");
            if (introspectedColumn.getRemarks() != null) {
                sb.append("\"");
                sb.append(introspectedColumn.getRemarks());
                sb.append("\"");
            }
            sb.append(")");
            field.addJavaDocLine(sb.toString());
            List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
            for (IntrospectedColumn primaryKeyColumn : primaryKeyColumns) {
                if (introspectedColumn.equals(primaryKeyColumn)) {
                    field.addJavaDocLine("@Id");
                }
            }
        }
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        properties.putAll(properties);
        this.suppressFileComments = StringUtility.isTrue(properties.getProperty("suppressFileComments"));
        super.addConfigurationProperties(properties);
    }
}
