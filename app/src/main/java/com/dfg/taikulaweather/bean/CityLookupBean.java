package com.dfg.taikulaweather.bean;/*
 * Create by KUTAI1 on 2023/11/10
 */

import java.util.List;

/**
 * @title:CityBean
 * @Author DFG
 * @Data：2023/11/1017:20 Vsersion 1.0
 */
public class CityLookupBean {

    /**
     * code : 200
     * location : [{"name":"北京","id":"101010100","lat":"39.90499","lon":"116.40529","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"10","fxLink":"https://www.qweather.com/weather/beijing-101010100.html"},{"name":"海淀","id":"101010200","lat":"39.95607","lon":"116.31032","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"https://www.qweather.com/weather/haidian-101010200.html"},{"name":"朝阳","id":"101010300","lat":"39.92149","lon":"116.48641","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"https://www.qweather.com/weather/chaoyang-101010300.html"},{"name":"昌平","id":"101010700","lat":"40.21809","lon":"116.23591","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"https://www.qweather.com/weather/changping-101010700.html"},{"name":"房山","id":"101011200","lat":"39.73554","lon":"116.13916","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"https://www.qweather.com/weather/fangshan-101011200.html"},{"name":"通州","id":"101010600","lat":"39.90249","lon":"116.65860","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"https://www.qweather.com/weather/tongzhou-101010600.html"},{"name":"丰台","id":"101010900","lat":"39.86364","lon":"116.28696","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"https://www.qweather.com/weather/fengtai-101010900.html"},{"name":"大兴","id":"101011100","lat":"39.72891","lon":"116.33804","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"https://www.qweather.com/weather/daxing-101011100.html"},{"name":"延庆","id":"101010800","lat":"40.46532","lon":"115.98501","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/yanqing-101010800.html"},{"name":"平谷","id":"101011500","lat":"40.14478","lon":"117.11234","adm2":"北京","adm1":"北京市","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/pinggu-101011500.html"}]
     * refer : {"sources":["QWeather"],"license":["QWeather Developers License"]}
     */

    private String code;//请求码
    private ReferBean refer;
    private List<LocationBean> location;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<LocationBean> getLocation() {
        return location;
    }

    public void setLocation(List<LocationBean> location) {
        this.location = location;
    }

    public static class ReferBean {
        //原始数据来源，或数据源说明，可能为空
        private List<String> sources;
        // 数据许可或版权声明，可能为空
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class LocationBean {
        /**
         * name : 北京
         * id : 101010100
         * lat : 39.90499
         * lon : 116.40529
         * adm2 : 北京
         * adm1 : 北京市
         * country : 中国
         * tz : Asia/Shanghai
         * utcOffset : +08:00
         * isDst : 0
         * type : city
         * rank : 10
         * fxLink : https://www.qweather.com/weather/beijing-101010100.html
         */

        private String name;//地区/城市名称
        private String id;//地区/城市ID
        private String lat;//地区/城市纬度
        private String lon;//地区/城市经度
        private String adm2;//地区/城市的上级行政区划名称
        private String adm1;//地区/城市所属一级行政区域
        private String country;//地区/城市所属国家名称
        private String tz;//地区/城市所在时区
        private String utcOffset;//地区/城市目前与UTC时间偏移的小时数
        private String isDst;//地区/城市是否当前处于夏令时。1 表示当前处于夏令时，0 表示当前不是夏令时。
        private String type;//地区/城市的属性
        private String rank;//地区评分
        private String fxLink;//该地区的天气预报网页链接，便于嵌入你的网站或应用

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getAdm2() {
            return adm2;
        }

        public void setAdm2(String adm2) {
            this.adm2 = adm2;
        }

        public String getAdm1() {
            return adm1;
        }

        public void setAdm1(String adm1) {
            this.adm1 = adm1;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public void setIsDst(String isDst) {
            this.isDst = isDst;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }
    }
}
