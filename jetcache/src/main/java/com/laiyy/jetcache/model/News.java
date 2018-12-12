//package com.laiyy.jetcache.model;
//
//import lombok.Data;
//
//import java.util.Date;
//
///**
// * Date:2018/1/17 0017
// * 新闻表
// */
//@Data
//public class News {
//    /**
//     * 新闻id
//     */
//    private int id;
//
//    /**
//     * 编辑id
//     */
//    private int userId;
//    /**
//     * 新闻状态
//     * 10初稿/50待审核/100已送签/150已签发/200草稿/250删除
//     */
//    private int status = 10;
//
//    /**
//     * 新闻恢复时用
//     * 新闻删除时的状态
//     * 10初稿/50待审核/100已送签/150已签发/200草稿/250删除/500定时新闻
//     */
//    private int delStatus = 0;
//
//    /**
//     * 新闻标题
//     */
//    private String title = "";
//    /**
//     * 新闻副标题
//     */
//    private String subtitle = "";
//    /**
//     * 新闻摘要
//     */
//
//    private String summary = "";
//    /**
//     * 创建时间
//     */
//    private Date createDate;
//    /**
//     * 成文时间
//     */
//    private Date writeDate;
//    /**
//     * 发布时间
//     */
//    private Date pubDate;
//    /**
//     * 定时发送时间
//     */
//    private Date beginTaskTime;
//    /**
//     * 定时撤回时间
//     */
//    private Date endTaskTime;
//    /**
//     * 新闻类型
//     * 10(普通)/50(文件)/100(公报)
//     */
//    private int type;
//
//    /**
//     * 新闻权重 0--普通稿件 1--优先稿件 2--紧急稿件  3--置顶稿件
//     */
//    private int newsWeigh;
//    /**
//     * 文章字数
//     */
//    private int contentNumber;
//    /**
//     * 站点id(站点唯一标识)
//     */
//    private String siteId;
//    /**
//     * 栏目id(栏目唯一标识)
//     */
//    private int channelId;
//    /**
//     * 栏目名称
//     */
//    private String channelName = "";
//    /**
//     * 模板id(模板唯一标识)
//     * 如果为0 则按照栏目的模板Id  如果栏目也为空 则按照站点的模板ID
//     */
//    private int templateId;
//    /**
//     * 来源
//     */
//    private String source = "";
//    /**
//     * 新闻原作者(填写)
//     */
//    private String author = "";
//    /**
//     * 关键字(多个英文,拼接)
//     */
//    private String keyword = "";
//    /**
//     * 新闻外链
//     */
//    private String outUrl = "";
//    /**
//     * 自身Url
//     */
//    private String selfUrl = "";
//    /**
//     * 焦点图1
//     */
//    private String focusPic1 = "";
//    /**
//     * 焦点图2
//     */
//    private String focusPic2 = "";
//    /**
//     * 焦点图3
//     */
//    private String focusPic3 = "";
//    /**
//     * 签发人Id
//     */
//    private int issuerId;
//    /**
//     * 签发人姓名
//     */
//    private String issuerName = "";
//
//    /**
//     * 是否首页显示
//     * 0 不显示 1显示
//     */
//    private int showStatus;
//    /**
//     * 是否显示标红
//     * 0 不标红 1标红
//     */
//    private int showRed;
//    /**
//     * 标题是否加粗
//     * 0 不加粗  1 加粗
//     */
//    private int showBold;
//    /**
//     * 拖拽排序字段
//     */
//    private int seq;
//    /**
//     * 索引号
//     */
//    private String reNum = "";
//    /**
//     * 公开类型
//     * 0(主动公开)/1(依申请公开)
//     */
//    private int openType;
//
//    /**
//     * 主题分类 走Xml
//     */
//    private int themeClass;
//
//    /**
//     * 主题分类名称
//     */
//    private String themeClassName = "";
//
//    /**
//     * 公文种类 走Xml
//     */
//    private int docType;
//
//    /**
//     * 公文种类名称
//     */
//    private String docTypeName = "";
//    /**
//     * 发文字号
//     */
//    private String docNum = "";
//    /**
//     * 发文机关
//     */
//    private String docAgency = "";
//    /**
//     * 主题词
//     */
//    private String themeWord = "";
//    /**
//     * 是否是多签新闻
//     * 0(否)/1(是)
//     */
//    private int moreAudit;
//    /**
//     * 是否是克隆新闻
//     * 0(否)/1(是)
//     */
//    private int clone;
//    /**
//     * 是否显示到手机客户端
//     * 0(否)/1(是)
//     */
//    private int mobile;
//    /**
//     * 视频地址
//     */
//    private String videoUrl = "";
//    /**
//     * 是否是退回稿件
//     */
//    private int backNews;
//
//    /**
//     * 是否是上传上来的 或者是下达下来的
//     * 0 什么都不是  1下达下来的  2是上传上来的
//     */
//    private int updown;
//    /**
//     * 操作人用户Id
//     */
//    private int operatorUserId;
//    /**
//     * 操作人姓名
//     */
//    private String operatorName = "";
//    /**
//     * 操作时间
//     */
//    private Date operatorDate;
//    /**
//     * 组织机构id
//     */
//    private int depId;
//    /**
//     * 组织机构名称
//     */
//    private String depName = "";
//
//    /**
//     * 总期号
//     */
//    private int bulletinTotal = 0;
//
//    /**
//     * 公报年份
//     */
//    private int bulletinYear = 0;
//    /**
//     * 公报期号
//     */
//    private int bulletinNum = 0;
//
//    /**
//     * 新闻标签，不入库，只做查询展示用
//     */
//    private String label = "";
//    /**
//     * 备用字段1
//     */
//    private String str0 = "";
//    /**
//     * 备用字段2
//     */
//    private String str1 = "";
//    /**
//     * 备用字段3
//     */
//    private String str2 = "";
//    /**
//     * 备用字段4
//     */
//    private String str3 = "";
//    /**
//     * 备用字段5
//     */
//    private String str4 = "";
//    /**
//     * 备用字段6
//     */
//    private String str5 = "";
//    /**
//     * 备用字段7
//     */
//    private String str6 = "";
//    /**
//     * 备用字段8
//     */
//    private String str7 = "";
//    /**
//     * 备用字段9
//     */
//    private String str8 = "";
//    /**
//     * 备用字段10
//     */
//    private String str9 = "";
//
//    // TODO: 2018/10/22 laiyy 新增字段
//
//    /**
//     * 栏目标志位
//     */
//    private String channelMarkId;
//
//    /**
//     * 新闻细览模版标识位
//     */
//    private String detailMarkId;
//
//}
