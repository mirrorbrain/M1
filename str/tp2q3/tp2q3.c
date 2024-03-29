/**
 * @file tp2q3.c
 *
 * @section desc File description
 *
 *
 * @section copyright Copyright
 *
 *
 * @section infos File informations
 *
 * $Date$ mar. févr.  7 17:45:10 CET 2017
 * $Rev$
 * $Author$ 14000806
 * $URL$ /private/student/6/06/14000806/M1git/M1/str/tp2q3
 */

#include "tpl_os.h"
#include "nxt_motors.h"
#include "ecrobot_interface.h"
#include "ecrobot_private.h"

int displayY = 0;

FUNC(int, OS_APPL_CODE) main(void)
{
    StartOS(OSDEFAULTAPPMODE);
    ShutdownOS(E_OK);
    return 0;
}

DeclareTask(task1);
DeclareTask(task2);
DeclareTask(task3);
DeclareTask(task0);


TASK(task0)
{
  ecrobot_init_sonar_sensor(NXT_PORT_S2);
  TerminateTask();
}

TASK(task1)
{
    display_goto_xy(0,0);
    display_string("temps : ");
    display_goto_xy(9,0);
    display_int(ecrobot_get_systick_ms(),0);
    display_update();
    TerminateTask();
}

TASK(task2)
{
    display_goto_xy(0,1);
    display_string("sensorR : ");
    display_goto_xy(11,1);
    display_int(ecrobot_get_touch_sensor(NXT_PORT_S1),0);
    display_goto_xy(0,2);
    display_string("sensorL : ");
    display_goto_xy(11,2);
    display_int(ecrobot_get_touch_sensor(NXT_PORT_S4),0);
    display_update();
    TerminateTask();
}

TASK(task3)
{

    display_goto_xy(0,3);
    display_string("distance : ");
    display_goto_xy(12,3);
    ecrobot_get_sonar_sensor(NXT_PORT_S2);
    display_update();
    TerminateTask();
}

ISR(isr_button_start)
{
    ecrobot_status_monitor("isr_button_start");

}

ISR(isr_button_stop)
{
    ShutdownOS(E_OK);
}

ISR(isr_button_left)
{
    ecrobot_status_monitor("isr_button_left");

}

ISR(isr_button_right)
{
    ecrobot_status_monitor("isr_button_right");

}

/* End of file tp2q3.c */
