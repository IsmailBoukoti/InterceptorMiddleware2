package co.develhope.interceptorsmiddleware2.interceptors;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> monthsList = Arrays.asList(new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "März"),
            new Month(4, "April", "Aprile", "April"),
            new Month(5, "May", "Maggio", "Mai"),
            new Month(6, "June", "Giugno", "Juni"));


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("monthNumber");
        if (header == null || header.isEmpty()) {
            response.setStatus(400);
            return false;
        }

        int monthNumber = Integer.parseInt(header);
        Month month = monthsList.stream().filter(m -> m.getMonthNumber() == monthNumber).findFirst().orElseGet(() -> new Month(0, "nope", "nope", "nope"));
        request.setAttribute("month", month);
        return true;
    }



    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}