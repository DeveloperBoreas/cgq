package com.boreas.api;

import com.boreas.base.BaseResponse;
import com.boreas.modle.ClipuesBean;
import com.boreas.modle.ComBean;
import com.boreas.modle.LoginReceBean;
import com.boreas.modle.PaperBean;
import com.boreas.modle.ProBean;
import com.boreas.modle.UserAdminReceBean;
import com.boreas.modle.UserInfo;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiService {

    String MEDIATYPE = "multipart/form-data";

    @POST("/login/login")
    @FormUrlEncoded
    Observable<LoginReceBean> login(@Field("userName") String username, @Field("userPassword") String password);

    @GET("/login/queryUserList")
    Observable<UserAdminReceBean> queryUserList();

    @GET("/queryClipue")
    Observable<ClipuesBean> queryClipue();

    @GET("/queryPro")
    Observable<ProBean> queryPro();

    @GET("/queryPaper")
    Observable<PaperBean> queryPaper();

    @GET("/queryCom")
    Observable<ComBean> queryCom();

    @POST("/insertClipue")
    @FormUrlEncoded
    Observable<BaseResponse> insertClipue(@Field("clipueName") String clipueName);

    @POST("/insertPro")
    Observable<BaseResponse> insertPro(@Body LoginReceBean.DataBean.ResearchPro pro);

    @POST("/insertPros")
    Observable<BaseResponse> insertPros(@Body ArrayList<LoginReceBean.DataBean.ResearchPro> pros);

    @POST("/updatePro")
    Observable<BaseResponse> updatePro(@Body LoginReceBean.DataBean.ResearchPro pro);

    @POST("/insertPaper")
    Observable<BaseResponse> insertPaper(@Body LoginReceBean.DataBean.ResearchPaper paper);

    @POST("/insertPapers")
    Observable<BaseResponse> insertPapers(@Body ArrayList<LoginReceBean.DataBean.ResearchPaper> papers);

    @POST("/updatePaper")
    Observable<BaseResponse> updatePaper(@Body LoginReceBean.DataBean.ResearchPaper paper);

    @POST("/insertComPositon")
    Observable<BaseResponse> insertComposition(@Body LoginReceBean.DataBean.Composition composition);

    @POST("/insertComPositons")
    Observable<BaseResponse> insertCompositions(@Body ArrayList<LoginReceBean.DataBean.Composition> compositions);


    @POST("/updateComPositon")
    Observable<BaseResponse> updateComPositon(@Body LoginReceBean.DataBean.Composition composition);

    @POST("/insertUserInfo")
    Observable<BaseResponse> insertUserInfo(@Body UserInfo userInfo);

    @POST("/forgetUserInfo")
    @FormUrlEncoded
    Observable<BaseResponse> forgetUserInfo(@Field("phone") String phone);

    @POST("/deleteById")
    @FormUrlEncoded
    Observable<BaseResponse> deleteById(@Field("id") int id);

    @POST("/updateUserInfo")
    Observable<BaseResponse> updateUserInfo(@Body UserInfo userInfo);

    @POST("/upload")
    @Multipart
    Observable<BaseResponse> uploadFile(@Part MultipartBody.Part body);

    @Streaming
    @GET("/download")
    Observable<Response<ResponseBody>> download(@Query("delete") boolean delete);
}
