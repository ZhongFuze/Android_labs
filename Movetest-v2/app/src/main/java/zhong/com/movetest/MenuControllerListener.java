package zhong.com.movetest;

import com.imangazaliev.circlemenu.*;

interface MenuControllerListener {

    void onOpenAnimationStart();

    void onOpenAnimationEnd();

    void onCloseAnimationStart();

    void onCloseAnimationEnd();

    void onSelectAnimationStart(com.imangazaliev.circlemenu.CircleMenuButton menuButton);

    void onSelectAnimationEnd(com.imangazaliev.circlemenu.CircleMenuButton menuButton);

    void redrawView();
}
