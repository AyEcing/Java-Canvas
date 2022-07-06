package com.microsoft.emmx;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * Android实现View隐藏显示渐变动画，动画工具
 */

public class ViewUtils {
    public enum AnimationState{
        STATE_SHOW,
        STATE_HIDDEN,
        STATE_SHOW_DOWN,
        STATE_SHOW_UP,
        STATE_SHOW_LEFT,
        STATE_SHOW_RIGHT,
        STATE_HIDDEN_DOWN,
        STATE_HIDDEN_UP,
        STATE_HIDDEN_LEFT,
        STATE_HIDDEN_RIGHT
        }
    /**
     * 渐隐渐现动画
     * @param view 需要实现动画的对象
     * @param state 需要实现的状态
     * @param duration 动画实现的时长（ms
     */
    public static void showAndHiddenAnimation(final View view,  final AnimationState state,  final long duration){

        view.post(new Runnable() {
                @Override
                public void run() {
                    int viewWidth=view.getMeasuredWidth()+50;
                    int viewHeight=view.getMeasuredHeight()+50;

                    float start = 0f;
                    float end = 0f;
                    if(state == AnimationState.STATE_SHOW || state == AnimationState.STATE_SHOW_DOWN || state == AnimationState.STATE_SHOW_UP
                       || state == AnimationState.STATE_SHOW_LEFT || state == AnimationState.STATE_SHOW_RIGHT){
                        end = 1f;
                        view.setVisibility(View.VISIBLE);
                    } else
                    if(state == AnimationState.STATE_HIDDEN || state == AnimationState.STATE_HIDDEN_DOWN || state == AnimationState.STATE_HIDDEN_UP
                       || state == AnimationState.STATE_HIDDEN_LEFT || state == AnimationState.STATE_HIDDEN_RIGHT){
                        start = 1f;
                        view.setVisibility(View.GONE);
                    }

                    AnimationSet animationSet = new AnimationSet(true);

                    //渐变的动画
                    AlphaAnimation animation = new AlphaAnimation(start, end);
                    animation.setDuration(duration);
                    animation.setFillAfter(true);
                    //平移动画
                    if(state == AnimationState.STATE_SHOW_DOWN ){
                        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,-viewHeight,0);//平移动画  从0,-100,平移到0,0
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_HIDDEN_DOWN){
                        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-viewHeight);//平移动画  从0,0,平移到0,-100
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_SHOW_UP ){
                        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,viewHeight,0);//平移动画  从0,100,平移到0,0
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_HIDDEN_UP){
                        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,viewHeight);//平移动画  从0,0,平移到0,100
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_SHOW_LEFT ){
                        TranslateAnimation translateAnimation = new TranslateAnimation(-viewWidth,0,0,0);//平移动画  从-100,0,平移到0,0
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_HIDDEN_LEFT){
                        TranslateAnimation translateAnimation = new TranslateAnimation(0,-viewWidth,0,0);//平移动画  从0,0,平移到-100,0
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_SHOW_RIGHT ){
                        TranslateAnimation translateAnimation = new TranslateAnimation(viewWidth,0,0,0);//平移动画  从100,0,平移到0,0
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }else if(state == AnimationState.STATE_HIDDEN_RIGHT){
                        TranslateAnimation translateAnimation = new TranslateAnimation(0,viewWidth,0,0);//平移动画  从0,0,平移到0,100
                        translateAnimation.setDuration(duration);
                        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
                        translateAnimation.setFillAfter(true);//不回到起始位置
                        animationSet.addAnimation(translateAnimation);
                    }
                    animationSet.addAnimation(animation);
                    animationSet.setAnimationListener(new Animation.AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                view.clearAnimation();
                            }
                        });

                    view.setAnimation(animationSet);
                    animationSet.start();
                }
            });
    }
}


