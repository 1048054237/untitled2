package com.yinta.entity;/**
 * Created by Administrator on 2019/1/4.
 */

import javax.persistence.*;

/**
 * @Author shijiawei
 * @date 2019/1/4
 */
@Entity
@Table(name = "amazonbtg2", schema = "", catalog = "amazon_ge")
public class Amazonbtg2Entity {
    private String query;
    private String url;
    private Integer nodeLevel;
    private String nodeName;
    private String nodePath;
    private String refinementLink;
    private String nodeId;

    @Basic
    @Column(name = "Query", nullable = true, insertable = true, updatable = true, length = 255)
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Basic
    @Column(name = "url", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "node_level", nullable = true, insertable = true, updatable = true)
    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    @Basic
    @Column(name = "node_name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Basic
    @Column(name = "node_path", nullable = true, insertable = true, updatable = true, length = 255)
    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    @Basic
    @Column(name = "refinement_link", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRefinementLink() {
        return refinementLink;
    }

    public void setRefinementLink(String refinementLink) {
        this.refinementLink = refinementLink;
    }

    @Id
    @Column(name = "node_id", nullable = false, insertable = true, updatable = true, length = 255)
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amazonbtg2Entity that = (Amazonbtg2Entity) o;

        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (nodeLevel != null ? !nodeLevel.equals(that.nodeLevel) : that.nodeLevel != null) return false;
        if (nodeName != null ? !nodeName.equals(that.nodeName) : that.nodeName != null) return false;
        if (nodePath != null ? !nodePath.equals(that.nodePath) : that.nodePath != null) return false;
        if (refinementLink != null ? !refinementLink.equals(that.refinementLink) : that.refinementLink != null)
            return false;
        if (nodeId != null ? !nodeId.equals(that.nodeId) : that.nodeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (nodeLevel != null ? nodeLevel.hashCode() : 0);
        result = 31 * result + (nodeName != null ? nodeName.hashCode() : 0);
        result = 31 * result + (nodePath != null ? nodePath.hashCode() : 0);
        result = 31 * result + (refinementLink != null ? refinementLink.hashCode() : 0);
        result = 31 * result + (nodeId != null ? nodeId.hashCode() : 0);
        return result;
    }
}
