package wgt.module.cn.com.wgt_sample.entity;

import java.util.List;

/**
 * Created by skc on 2020/6/19.
 */
public class ReportSubmitRequestEntity {
    private String reportIds;
    private String clueDescribe;
    private String toVillageMgr;
    private List<String> imageArray;
    private List<String> audioArray;
    private List<String> videoArray;


    public ReportSubmitRequestEntity(String reportIds, String clueDescribe, String toVillageMgr, List<String> imageArray, List<String> audioArray, List<String> videoArray) {
        this.reportIds = reportIds;
        this.clueDescribe = clueDescribe;
        this.toVillageMgr = toVillageMgr;
        this.imageArray = imageArray;
        this.audioArray = audioArray;
        this.videoArray = videoArray;
    }

    public String getReportIds() {
        return reportIds;
    }

    public void setReportIds(String reportIds) {
        this.reportIds = reportIds;
    }

    public String getClueDescribe() {
        return clueDescribe;
    }

    public void setClueDescribe(String clueDescribe) {
        this.clueDescribe = clueDescribe;
    }

    public String isToVillageMgr() {
        return toVillageMgr;
    }

    public void setToVillageMgr(String toVillageMgr) {
        this.toVillageMgr = toVillageMgr;
    }


    public List<String> getImageArray() {
        return imageArray;
    }

    public void setImageArray(List<String> imageArray) {
        this.imageArray = imageArray;
    }

    public List<String> getAudioArray() {
        return audioArray;
    }

    public void setAudioArray(List<String> audioArray) {
        this.audioArray = audioArray;
    }

    public List<String> getVideoArray() {
        return videoArray;
    }

    public void setVideoArray(List<String> videoArray) {
        this.videoArray = videoArray;
    }


    @Override
    public String toString() {
        return "ReportSubmitRequestEntity{" +
                "reportIds='" + reportIds + '\'' +
                ", clueDescribe='" + clueDescribe + '\'' +
                ", toVillageMgr='" + toVillageMgr + '\'' +
                ", imageArray=" + imageArray +
                ", audioArray=" + audioArray +
                ", videoArray=" + videoArray +
                '}';
    }
}
